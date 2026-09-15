package org.pricingengine;

import org.pricingengine.engine.PricingEngine;
import org.pricingengine.model.record.CartEvaluation;
import org.pricingengine.model.record.CartLineResult;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        PricingEngine engine = new PricingEngine();

        engine.addProduct("Shirt", 1000);
        engine.addProduct("Shoes", 2000);
        engine.addProduct("Socks", 500);

        engine.createPercentageOffer("OFFER1", List.of("Shirt", "Socks"), 10, 2, 2);
        engine.createFlatOffer("OFFER2", List.of("Shoes"), 300, 1, 5);
        engine.createPercentageOffer("OFFER3", List.of("Shoes"), 20, 2, 1);

        engine.createCart("Cart1");
        engine.addToCart("Cart1", "Shirt", 2);
        engine.addToCart("Cart1", "Shoes", 2);

        printEvaluation("Cart1 (preview)", engine.evaluateCart("Cart1"));
        printEvaluation("Cart1 (checkout)", engine.checkoutCart("Cart1"));

        System.out.println("  OFFER1 remaining uses: " + engine.getOffer("OFFER1").getRemainingUses()
                + ", OFFER3 remaining uses: " + engine.getOffer("OFFER3").getRemainingUses());
        System.out.println();

        engine.createCart("Cart2");
        engine.addToCart("Cart2", "Shirt", 2);
        engine.addToCart("Cart2", "Shoes", 2);
        printEvaluation("Cart2 (checkout)", engine.checkoutCart("Cart2"));
        System.out.println("  OFFER1 remaining uses: " + engine.getOffer("OFFER1").getRemainingUses());
        System.out.println();

        engine.createCart("Cart3");
        engine.addToCart("Cart3", "Socks", 3);
        printEvaluation("Cart3 (preview)", engine.evaluateCart("Cart3"));
        System.out.println();

        // test for concurrent checkouts
        runConcurrencyTest();
    }

    private static void printEvaluation(String label, CartEvaluation evaluation) {
        System.out.println(label);
        for (CartLineResult line : evaluation.lines()) {
            String offersSummary = line.validOffers().isEmpty() ? "None" : line.validOffers().stream()
                        .map(quote -> quote.offerId() + " (" + quote.description() + " = " + quote.discount() + ")")
                        .collect(Collectors.joining(", "));
            String applied = line.appliedOfferId() == null ? "None" : line.appliedOfferId();
            System.out.println("  " + line.productId() + ": Qty " + line.quantity()
                    + ". Base = " + line.baseTotal()
                    + ". Valid Offers: " + offersSummary
                    + ". Best Offer Applied: " + applied
                    + ". Discount = " + line.discount()
                    + ". Final = " + line.finalPrice() + ".");
        }
        System.out.println("  Cart Total: Base = " + evaluation.totalBase() + ". Total Discount = " + evaluation.totalDiscount()
                + ". Final Amount To Pay = " + evaluation.finalAmount() + ".");
    }

    /* Demonstrates concurrency management: many carts, each wanting the same globally
       capped offer, check out at the same time.
     */
    private static void runConcurrencyTest() throws InterruptedException {
        PricingEngine engine = new PricingEngine();
        engine.addProduct("Cap", 100);
        engine.createFlatOffer("LIMITED", List.of("Cap"), 10, 1, 5);

        int cartCount = 20;
        ExecutorService executor = Executors.newFixedThreadPool(cartCount);
        CountDownLatch latch = new CountDownLatch(cartCount);
        AtomicInteger offerWinners = new AtomicInteger();

        for (int i = 0; i < cartCount; i++) {
            String cartId = "concurrent-cart-" + i;
            engine.createCart(cartId);
            engine.addToCart(cartId, "Cap", 1);
            executor.submit(() -> {
                CartEvaluation result = engine.checkoutCart(cartId);
                if ("LIMITED".equals(result.lines().get(0).appliedOfferId())) {
                    offerWinners.incrementAndGet();
                }
                latch.countDown();
            });
        }

        latch.await(10, TimeUnit.SECONDS);
        executor.shutdown();

        System.out.println("Concurrency test: " + cartCount + " carts checked out concurrently, "
                + offerWinners.get() + " won the 5-use LIMITED offer (remaining uses = "
                + engine.getOffer("LIMITED").getRemainingUses() + ").");
    }
}
