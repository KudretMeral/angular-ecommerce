package com.meral.ecommerce.controller;


import com.meral.ecommerce.dto.PurchaseDto;
import com.meral.ecommerce.dto.PurchaseResponseDto;
import com.meral.ecommerce.service.checkout.CheckoutService;
import com.meral.ecommerce.service.checkout.CheckoutServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/checkout")
public class CheckoutController {


    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private CheckoutServiceImpl checkoutServiceImpl;


    @PostMapping("/purchase")
    public PurchaseResponseDto placeOrder(@RequestBody PurchaseDto purchaseDto)
    {

        PurchaseResponseDto purchaseResponseDto=checkoutService.placeOrder(purchaseDto);

        return purchaseResponseDto;
    }



}
