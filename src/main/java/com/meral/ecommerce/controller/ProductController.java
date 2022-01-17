package com.meral.ecommerce.controller;

import com.meral.ecommerce.model.Product;
import com.meral.ecommerce.service.ProductService;
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

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public JSONObject getAllProducts() {
        List<Product> productList = productService.getAllProducts();

        JSONObject jo = new JSONObject();
        jo.put("products", productList);

        return jo;
    }

    @RequestMapping(value = "/getProduct/{id}")
    public Optional<Product> getProduct(@PathVariable("id") Long id) {
        Optional<Product> product = productService.getProduct(id);

return product;

    }

   /* @RequestMapping(value = "/getProduct/{id}")
    public JSONObject getProduct(@PathVariable("id") Long id) {
      Optional<Product> product = productService.getProduct(id);
      List<Optional<Product>> productList= new ArrayList<>();
        productList.add(product);
        JSONObject jo = new JSONObject();
        jo.put("products", product);
        System.out.print(product.get().getId());

        return jo;

    }*/

    @RequestMapping(value = "/search{name}", method = RequestMethod.GET)
    public JSONObject findByName(@RequestParam("name") String name) {
        JSONObject jo = new JSONObject();
        List<Product> productList = new ArrayList<>();

        productList = productService.findByName(name);
        jo.put("products", productList);
        return jo;

    }

    @RequestMapping(value = "/findByCategoryId{id}", method = RequestMethod.GET)
    public JSONObject findByCategoryId(@RequestParam("id") Long id) {
        JSONObject jo = new JSONObject();
        List<Product> productList = productService.findByCategoryId(id);
        jo.put("products", productList);
        return jo;
    }

}
