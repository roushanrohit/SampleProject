package org.example.designpatterns.structural.proxy;

/*
    Proxy Pattern helps in:
    1. Lazy loading of heavy objects -- saving memory,disk and cpu
    2. Restrict access to critical objects and expose only what is required
    3. Caching
    4. Separation of concerns -- the real object only deals with its core
       responsibilities whereas the proxy handles ancillary operations like
       initialization or security
 */
public class Main {

    public static void main(String[] args) {

        // creation of proxy images is very fast .... no disk operations
        Image image1 = new ProxyImage("dog.java");
        // image2 is never loaded from the disk
        Image image2 = new ProxyImage("dog.java");

        // image1 is loaded and displayed
        image1.display();
        // image1 was already loaded... it is just displayed ... very fast
        image1.display();
    }
}
