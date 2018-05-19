package com.adidas.repository;

import java.util.List;

import com.adidas.model.entity.BookStore;
import com.adidas.model.entity.BookStoreWithBooks;

/**
 * @author Error404
 */
public interface BookStoreRepository {

    BookStore selectBookStoreById(Long id);

    List<BookStore> selectAllBookStores();

    BookStoreWithBooks selectBookStoreWithBooksById(Long id);

}
