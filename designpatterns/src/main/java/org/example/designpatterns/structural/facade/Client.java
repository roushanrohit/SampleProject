package org.example.designpatterns.structural.facade;

/*
    Client only interacts with api-gateway.
    1. No tight coupling between client code and microservices
    2. Inner workings of the microservices are not exposed to the client
    3. Reduced network latency since number of calls between client and server is reduced.
    4. Client code is simplified.
    5. Ancillary responsibilities like authentication, authorization, rate limiting etc are handled by the
       API Gateway and the respective microservices do not need to handle it.
    6. Internal APIs can become complex over time but the client interface will not change(open/closed principle)
 */
public class Client {

    public static void main(String[] args) {

        APIGateway apiGateway = new APIGateway();
        String userId = "123";
        String orderId = "abc456";
        String fullOrderDetails = apiGateway.getFullOrderDetails(userId, orderId);
        System.out.println(fullOrderDetails);
    }
}
