package org.pricingengine.engine;

import org.pricingengine.exception.CartAlreadyCheckedOutException;
import org.pricingengine.exception.CartNotFoundException;
import org.pricingengine.exception.DuplicateCartException;
import org.pricingengine.exception.DuplicateOfferException;
import org.pricingengine.exception.DuplicateProductException;
import org.pricingengine.exception.OfferNotFoundException;
import org.pricingengine.exception.ProductNotFoundException;
import org.pricingengine.offer.OfferFactory;
import org.pricingengine.model.Cart;
import org.pricingengine.model.record.CartEvaluation;
import org.pricingengine.model.record.CartLineResult;
import org.pricingengine.model.record.OfferQuote;
import org.pricingengine.model.Product;
import org.pricingengine.offer.Offer;
import org.pricingengine.strategy.BestSingleOfferStrategy;
import org.pricingengine.strategy.OfferSelectionStrategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class PricingEngine {

    private final Map<String, Product> products = new ConcurrentHashMap<>();
    private final Map<String, Offer> offers = new ConcurrentHashMap<>();
    private final Map<String, List<Offer>> offersByProduct = new ConcurrentHashMap<>();
    private final Map<String, Cart> carts = new ConcurrentHashMap<>();
    private final OfferSelectionStrategy selectionStrategy;

    public PricingEngine() {
        this(new BestSingleOfferStrategy());
    }

    public PricingEngine(OfferSelectionStrategy selectionStrategy) {
        this.selectionStrategy = selectionStrategy;
    }

    public void addProduct(String productId, double basePrice) {
        Product product = new Product(productId, BigDecimal.valueOf(basePrice));
        if (products.putIfAbsent(productId, product) != null) {
            throw new DuplicateProductException(productId);
        }
    }

    public void createPercentageOffer(String offerId, List<String> productIds, double discountPercentage,
                                      int minQuantity, int maxGlobalUses) {
        registerOffer(OfferFactory.percentageOffer(offerId, new LinkedHashSet<>(productIds),
                minQuantity, maxGlobalUses, discountPercentage));
    }

    public void createFlatOffer(String offerId, List<String> productIds, double discountAmount,
                                int minQuantity, int maxGlobalUses) {
        registerOffer(OfferFactory.flatOffer(offerId, new LinkedHashSet<>(productIds),
                minQuantity, maxGlobalUses, discountAmount));
    }

    public void createCart(String cartId) {
        Cart cart = new Cart(cartId);
        if (carts.putIfAbsent(cartId, cart) != null) {
            throw new DuplicateCartException(cartId);
        }
    }

    public void addToCart(String cartId, String productId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be > 0: " + quantity);
        }
        Cart cart = getCart(cartId);
        if (cart.isCheckedOut()) {
            throw new CartAlreadyCheckedOutException(cartId);
        }
        Product product = products.get(productId);
        if (product == null) {
            throw new ProductNotFoundException(productId);
        }
        cart.addItem(productId, quantity);
    }

    // Read-only: does not reserve offer usage, so it can be called any number of times while the user is still deciding.
    public CartEvaluation evaluateCart(String cartId) {
        Cart cart = getCart(cartId);
        if (cart.isCheckedOut()) {
            return cart.getCheckoutSnapshot();
        }
        return evaluate(cart, false);
    }

    // Locks in offer usage against each applied offer's global cap.
    // Idempotent per cart: a second call on an already checked-out cart throws cart already checked out exception
    public CartEvaluation checkoutCart(String cartId) {
        Cart cart = getCart(cartId);
        if (!cart.markCheckedOut()) {
            throw new CartAlreadyCheckedOutException(cartId);
        }
        CartEvaluation result = evaluate(cart, true);
        cart.setCheckoutSnapshot(result);
        return result;
    }

    private void registerOffer(Offer offer) {
        for (String productId : offer.getProductIds()) {
            getProduct(productId);
        }
        if (offers.putIfAbsent(offer.getOfferId(), offer) != null) {
            throw new DuplicateOfferException(offer.getOfferId());
        }
        for (String productId : offer.getProductIds()) {
            offersByProduct.computeIfAbsent(productId, key -> new CopyOnWriteArrayList<>()).add(offer);
        }
    }

    private CartEvaluation evaluate(Cart cart, boolean commitUsage) {
        List<CartLineResult> lines = new ArrayList<>();
        BigDecimal totalBase = BigDecimal.ZERO;
        BigDecimal totalDiscount = BigDecimal.ZERO;

        for (Map.Entry<String, Integer> entry : cart.snapshotItems().entrySet()) {
            String productId = entry.getKey();
            int quantity = entry.getValue();
            Product product = getProduct(productId);
            BigDecimal lineBase = product.getBasePrice().multiply(BigDecimal.valueOf(quantity));

            // get all offers eligible for the product
            List<Offer> candidates = offersByProduct.getOrDefault(productId, List.of());
            List<OfferQuote> validQuotes = new ArrayList<>();
            List<Offer> eligible = new ArrayList<>();
            for (Offer offer : candidates) {
                if (offer.isEligible(quantity)) {
                    BigDecimal discount = offer.calculateDiscount(quantity, lineBase);
                    validQuotes.add(new OfferQuote(offer.getOfferId(), offer.getDescription(), discount));
                    eligible.add(offer);
                }
            }

            String appliedOfferId = null;
            BigDecimal discount = BigDecimal.ZERO;

            if (commitUsage) {
                List<Offer> remaining = new ArrayList<>(eligible);
                while (!remaining.isEmpty()) {
                    Offer choice = selectionStrategy.select(remaining, quantity, lineBase);
                    if (choice.tryReserveUse()) {
                        appliedOfferId = choice.getOfferId();
                        discount = choice.calculateDiscount(quantity, lineBase);
                        break;
                    }
                    /* lost the race for that offer's last use
                       we'll remove this offer from the list of remaining offers
                       and try to get the next best offer
                     */
                    remaining.remove(choice);
                }
            } else {
                Offer choice = selectionStrategy.select(eligible, quantity, lineBase);
                if (choice != null) {
                    appliedOfferId = choice.getOfferId();
                    discount = choice.calculateDiscount(quantity, lineBase);
                }
            }

            BigDecimal finalPrice = lineBase.subtract(discount);
            lines.add(new CartLineResult(productId, quantity, lineBase, validQuotes, appliedOfferId, discount, finalPrice));
            totalBase = totalBase.add(lineBase);
            totalDiscount = totalDiscount.add(discount);
        }

        return new CartEvaluation(cart.getCartId(), lines, totalBase, totalDiscount, totalBase.subtract(totalDiscount));
    }

    private Product getProduct(String productId) {
        Product product = products.get(productId);
        if (product == null) {
            throw new ProductNotFoundException(productId);
        }
        return product;
    }

    private Cart getCart(String cartId) {
        Cart cart = carts.get(cartId);
        if (cart == null) {
            throw new CartNotFoundException(cartId);
        }
        return cart;
    }

    public Offer getOffer(String offerId) {
        Offer offer = offers.get(offerId);
        if (offer == null) {
            throw new OfferNotFoundException(offerId);
        }
        return offer;
    }
}
