package org.example.designpatterns.structural.facade;

public class APIGateway {

    private final UserService userService;
    private final OrderService orderService;
    private final PaymentService paymentService;

    public APIGateway(){
        this.userService = new UserService();
        this.orderService = new OrderService();
        this.paymentService = new PaymentService();
    }


    public String getFullOrderDetails(String userId, String orderId) {

        return userService.fetchUserDetails(userId) + "\n" + orderService.processOrder(orderId)
                + "\n" + paymentService.processPayment(userId, orderId);
    }
}
