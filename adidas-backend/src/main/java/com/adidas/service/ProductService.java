package com.adidas.service;

import java.util.List;
import java.util.Optional;

import com.adidas.model.entity.Product;
import com.adidas.model.entity.UserActivityData;

/**
 * @author Error404
 */
public interface ProductService {

	public Optional<Product> getProductByActivityId(Long idActivity);
}
