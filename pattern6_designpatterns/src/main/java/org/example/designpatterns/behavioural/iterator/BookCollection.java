package org.example.designpatterns.behavioural.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookCollection implements Iterable<Book>{

    private final List<Book> bookCollection = new ArrayList<>();

    public void addBook(Book book){
        bookCollection.add(book);
    }

    @Override
    public Iterator<Book> iterator() {
        return new BookIterator(this.bookCollection);
    }

    // Nested Class
    private class BookIterator implements Iterator<Book> {

        private List<Book> books;
        private int position;

        public BookIterator(List<Book> books){
            this.books = books;
            position = 0;
        }

        @Override
        public boolean hasNext() {
            return position < books.size();
        }

        @Override
        public Book next() {
            return books.get(position++);
        }
    }
}
