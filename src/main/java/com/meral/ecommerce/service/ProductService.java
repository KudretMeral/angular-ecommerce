package com.meral.ecommerce.service;


import com.meral.ecommerce.model.Product;
import com.meral.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;




    public List<Product> findByName(String name)
    {
        return productRepository.findByNameContaining(name);
    }

    public List<Product> findByCategoryId(Long id)
    {

        return productRepository.findByCategoryId(id);
    }

    public Optional<Product> getProduct(Long id)
    {
        Optional<Product> product=productRepository.findById(id);
        return product;
    }

    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

}
