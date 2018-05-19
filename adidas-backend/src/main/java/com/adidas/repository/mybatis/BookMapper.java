package com.adidas.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.adidas.model.entity.Book;
import com.adidas.repository.BookRepository;

import java.util.List;

/**
 * @author Error404
 */
@Mapper
public interface BookMapper extends BookRepository {

    @Override
    List<Book> selectBooksByLowPriceAndHighPrice(@Param("lowPrice") Double lowPrice, @Param("highPrice") Double highPrice);

    @Override
    List<Book> selectBooksByPage(@Param("offset") Integer offset, @Param("perPage") Integer perPage);

}
