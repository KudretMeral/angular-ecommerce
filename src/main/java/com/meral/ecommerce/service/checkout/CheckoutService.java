package com.meral.ecommerce.service.checkout;

import com.meral.ecommerce.dto.PurchaseDto;
import com.meral.ecommerce.dto.PurchaseResponseDto;

public interface CheckoutService {

    PurchaseResponseDto placeOrder(PurchaseDto purchaseDto);

}
