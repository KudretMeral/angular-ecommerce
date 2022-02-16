package com.meral.ecommerce.service.checkout;

import com.meral.ecommerce.dto.PurchaseDto;
import com.meral.ecommerce.dto.PurchaseResponseDto;
import com.meral.ecommerce.model.Customer;
import com.meral.ecommerce.model.Order;
import com.meral.ecommerce.model.OrderItem;
import com.meral.ecommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;


@Service
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public PurchaseResponseDto placeOrder(PurchaseDto purchaseDto) {

        PurchaseResponseDto purchaseResponseDto = new PurchaseResponseDto();

        //Set Order
        String orderTrackingNumber = generateOrderTrackingNumber();  //Takip numarası

        Order order = purchaseDto.getOrder();
        order.setOrderTrackingNumber(orderTrackingNumber);

        List<OrderItem> orderItemList = purchaseDto.getOrderItemList();

        orderItemList.forEach(orderItem -> {
            order.add(orderItem);
        });
        order.setBillingAddress(purchaseDto.getBillingAddress());
        order.setShippingAddress(purchaseDto.getShippingAddress());


        //Set Customer

        Customer customer = purchaseDto.getCustomer();
        customer.add(order);

        customerRepository.save(customer);

        purchaseResponseDto.setOrderTrackingNumber(orderTrackingNumber);

        return purchaseResponseDto;

    }


    private String generateOrderTrackingNumber() {

        return UUID.randomUUID().toString();
    }

}
