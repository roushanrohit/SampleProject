package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class StreamsExample {

    public static void main(String[] args) {

        List<User2> userList = new ArrayList<>();
        userList.add(new User2("Delhi", "Raman", 100L));
        userList.add(new User2("Pune", "Rohit", 98L));
        userList.add(new User2("Mumbai", "Raj", 100L));
        userList.add(new User2("Delhi", "Aishwarya", 98L));

        // groupingBy
        Map<String, List<User2>> usersByCity = userList.stream().collect(Collectors.groupingBy(User2::getCity));
        System.out.println(usersByCity);

        // user having max score for a city
        Map<String, Optional<User2>> usersMaxScoreByCity = userList.stream().collect(Collectors.groupingBy(User2::getCity,
                Collectors.maxBy(Comparator.comparingDouble(User2::getScore))));
        System.out.println(usersMaxScoreByCity);

        // toMap
        Map<String, Long> scores = userList.stream().collect(Collectors.toMap(User2::getName, User2::getScore));
        System.out.println(scores);
    }
}
