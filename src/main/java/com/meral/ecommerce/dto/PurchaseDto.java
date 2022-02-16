package com.meral.ecommerce.dto;

import com.meral.ecommerce.model.Address;
import com.meral.ecommerce.model.Customer;
import com.meral.ecommerce.model.Order;
import com.meral.ecommerce.model.OrderItem;

import java.util.List;

public class PurchaseDto {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;

    private List<OrderItem> orderItemList;


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
