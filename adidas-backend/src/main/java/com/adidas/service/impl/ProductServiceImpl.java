package com.adidas.service.impl;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adidas.model.entity.Product;
import com.adidas.model.entity.SensorData;
import com.adidas.model.entity.UserActivityData;
import com.adidas.repository.ProductRepository;
import com.adidas.repository.SensorDataRepository;
import com.adidas.repository.UserActivityDataRepository;
import com.adidas.service.ProductService;
import com.adidas.service.UserActivityDataService;
import com.adidas.util.PageUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

/**
 * @author Error404
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
   
    @Override
    public Optional<Product> getProductByActivityId(Long idActivity) {
        return Optional.ofNullable(productRepository.selectByIdActivity(idActivity));
    }
   

    
}
