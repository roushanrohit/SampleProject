package org.example.designpatterns.behavioural.iterator;

import java.util.*;

public class BookCollectionV2 implements Iterable<Book>{

    private final Set<Book> bookCollection = new TreeSet<>();

    public void addBook(Book book){
        bookCollection.add(book);
    }

    @Override
    public Iterator<Book> iterator() {
        return bookCollection.iterator();
    }
}
