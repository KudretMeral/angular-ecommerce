package com.meral.ecommerce.service;


import com.meral.ecommerce.model.Product;
import com.meral.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;


    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

}
