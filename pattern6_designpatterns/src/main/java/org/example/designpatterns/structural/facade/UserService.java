package org.example.designpatterns.structural.facade;

/*
    Microservice to fetch user details
 */
public class UserService {

    public String fetchUserDetails(String userId){

        // could be a database operation, but now we're just simulating it
        return "User Details : " + userId;
    }
}
