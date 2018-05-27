package com.adidas.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.adidas.model.entity.Book;
import com.adidas.repository.BookRepository;
import com.adidas.repository.UserActivityDataRepository;

import java.util.List;

/**
 * @author Error404
 */
@Mapper
public interface UserActivityDataMapper extends UserActivityDataRepository {



}
