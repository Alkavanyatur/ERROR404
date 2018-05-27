package com.adidas.repository;

import org.apache.ibatis.annotations.Param;

import com.adidas.model.entity.Product;


/**
 * @author Error404
 */
public interface ProductRepository {
    
    Product selectByIdActivity(@Param("idActivity") Long idActivity);
    
}
