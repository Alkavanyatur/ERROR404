package com.adidas.repository;

import java.util.List;

import com.adidas.model.entity.Book;
import com.adidas.model.entity.BookWithBookStore;

/**
 * @author Error404
 */
public interface BookRepository {

    Book selectBookById(Long id);

    List<Book> selectBooksByAuthor(String author);

    List<Book> selectBooksByLowPriceAndHighPrice(Double lowPrice, Double highPrice);

    List<Book> selectAllBooks();

    List<Book> selectBooksByPage(Integer offset, Integer perPage);

    BookWithBookStore selectBookWithBookStoreById(Long id);

    Integer selectCount();

    Integer insertBook(Book book);

    Integer updateBookOnNameById(Book book);

    Integer deleteBookById(Long id);

}
