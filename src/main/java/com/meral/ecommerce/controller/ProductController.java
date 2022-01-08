package com.meral.ecommerce.controller;

import com.meral.ecommerce.model.Product;
import com.meral.ecommerce.service.ProductService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public JSONObject  getAllProducts() {
        List<Product> productList = productService.getAllProducts();

        JSONObject jo = new JSONObject();
        jo.put("products",productList);

        return jo;
    }

}
