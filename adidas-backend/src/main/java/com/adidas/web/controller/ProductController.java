package com.adidas.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.adidas.constant.ResourceNameConstant;
import com.adidas.service.ProductService;
import com.adidas.service.UserActivityDataService;
import com.adidas.web.exception.ResourceNotFoundException;

import java.net.URI;

/**
 * @author Error404
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/idActivity/{idActivity}")
    public ResponseEntity<?> getProductByIdActivity(@PathVariable Long idActivity) {
        return productService
                .getProductByActivityId(idActivity)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException()
                        .setResourceName(ResourceNameConstant.PRODUCT)
                        .setId(idActivity));
    }


}
