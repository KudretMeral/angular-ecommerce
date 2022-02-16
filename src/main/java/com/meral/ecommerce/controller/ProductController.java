package com.meral.ecommerce.controller;

import com.meral.ecommerce.model.Product;
import com.meral.ecommerce.service.ProductService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    ProductService productService;

  /*  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public JSONObject getAllProducts() {
        List<Product> productList = productService.getAllProducts();
        JSONObject jo = new JSONObject();

        jo.put("products", productList);

        return jo;
    }
    */



    @RequestMapping(value = "/getProduct/{id}")
    public Optional<Product> getProduct(@PathVariable("id") Long id) {
        Optional<Product> product = productService.getProduct(id);

        return product;

    }


    @RequestMapping(value = "/search{name}", method = RequestMethod.GET)
    public Page<Product> findByName(Pageable page, @RequestParam("name") String name) {
        JSONObject jo = new JSONObject();
       // List<Product> productList = new ArrayList<>();

       // productList = productService.findByName(name);
        Page<Product> productList=productService.findByNamePageable(page,name);

        return productList;

    }

    @RequestMapping(value = "/findByCategoryId{id}", method = RequestMethod.GET)
    public Page<Product> findByCategoryId(Pageable page,@RequestParam("id") Long id) {

       Page<Product> productList = productService.findByCategoryIdPageable(page,id);


        return productList;
    }

}
