package com.adidas.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.adidas.model.entity.Book;
import com.adidas.model.entity.BookWithBookStore;
import com.adidas.model.entity.UserActivityData;

/**
 * @author Error404
 */
public interface UserActivityDataRepository {

    Integer insert(UserActivityData userActivityData);
    
    List<UserActivityData> selectActivitiesByUserId(@Param("idUser") Long idUser);
    
    List<UserActivityData> selectUserActivityDataByUserActivity(@Param("idUser") Long idUser, @Param("idActivity") Long idActivity);
    
}
