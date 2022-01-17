package com.meral.ecommerce.service;


import com.meral.ecommerce.model.ProductCategory;
import com.meral.ecommerce.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCategoryService {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    public List<ProductCategory> getAllCategories()
    {
        List<ProductCategory> productCategoryList= new ArrayList<>();

        productCategoryList=productCategoryRepository.findAll();
        return productCategoryList;
    }

}
