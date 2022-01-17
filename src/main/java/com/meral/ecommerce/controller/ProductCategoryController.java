package com.meral.ecommerce.controller;


import com.meral.ecommerce.model.ProductCategory;
import com.meral.ecommerce.service.ProductCategoryService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/product-category")
public class ProductCategoryController {


    @Autowired
    ProductCategoryService productCategoryService;

    @RequestMapping(value = "/getAll")
    public JSONObject getAllCategory() {
        JSONObject jo = new JSONObject();

        List<ProductCategory> productCategoryList =productCategoryService.getAllCategories();

        jo.put("productCategory",productCategoryList);
        return jo;

    }

}
