package com.tms.dao;

import com.tms.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookDBStore {
    private List<Book> books = new ArrayList<>();

    private static BookDBStore bookDBStore = null;

    private BookDBStore() {
    }

    public static BookDBStore getBookDBStore() {
        if (bookDBStore != null) {
            return bookDBStore;
        } else {
            synchronized (BookDBStore.class) {
                if (bookDBStore == null) {
                    bookDBStore = new BookDBStore();
                }
            }
        }
        return bookDBStore;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public Book findWithUUID(UUID uuid) {
        Book bookForRemove = books
                .stream()
                .filter(book -> book.getUuid().equals(uuid))
                .findFirst().orElse(null);
        return bookForRemove;
    }

    public List<Book> getList() {
        return books;
    }

}
