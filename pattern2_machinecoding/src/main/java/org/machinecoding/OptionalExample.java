package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalExample {

    public static void main(String[] args) {

        List<User2> userList = new ArrayList<>();
        userList.add(new User2("Delhi", "Raman", 100L));
        userList.add(new User2("Pune", "Rohit", 98L));
        userList.add(new User2("Mumbai", "Raj", 100L));
        userList.add(new User2("Delhi", "Aishwarya", 98L));
        
        Optional<User2> u = userList.stream().filter(x -> x.getName().equals("Rohit2")).findAny();
        //u.ifPresent(x -> System.out.println(x.getCity()));
        System.out.println(u.orElse(null));

        //Optional<String> name = Optional.of("Rohit");
        Optional<String> name = Optional.empty();
        System.out.println(name.orElse("No name found"));

    }
}
