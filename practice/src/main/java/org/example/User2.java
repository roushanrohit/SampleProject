package org.example;

public class User2 {
    String city;
    String name;
    Long score;

    public User2(String city, String name, Long score) {
        this.city = city;
        this.name = name;
        this.score = score;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "User{" +
                "city='" + city + '\'' +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
