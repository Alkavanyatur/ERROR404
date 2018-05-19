package com.adidas.service;

import java.util.List;
import java.util.Optional;

import com.adidas.model.entity.BookStore;
import com.adidas.model.entity.BookStoreWithBooks;

/**
 * @author Error404
 */
public interface BookStoreService {

    Optional<BookStore> getBookStoreById(Long id);

    List<String> getAllBookStoreNames();

    Optional<BookStoreWithBooks> getBookStoreWithBooksById(Long id);

}
