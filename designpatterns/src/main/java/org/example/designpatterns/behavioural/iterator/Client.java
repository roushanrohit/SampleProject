package org.example.designpatterns.behavioural.iterator;

import java.util.Iterator;

/*
    We have a collection, and we need to provide a mechanism for
    accessing its elements, without the iterator pattern, the client
    needs to understand how the collection is structured, and different collections
    would require different methods to traverse them.

    A common interface that the client can use to traverse any kind of collection - Iterator interface
 */
public class Client {

    public static void main(String[] args) {

        BookCollectionV2 bookCollection = new BookCollectionV2();
        bookCollection.addBook(new Book("C++"));
        bookCollection.addBook(new Book("Java"));

        /*
            Provides a way to traverse a collection without revealing the underlying structure
            Gives a uniform interface for traversal
            We can also implement multiple types of iterators - forward iterators, backward iterators
            without changing the collection.
         */
        Iterator<Book> iterator = bookCollection.iterator(); // standard across all collections
        while(iterator.hasNext()){
            System.out.println(iterator.next().getTitle());
        }
    }
}
