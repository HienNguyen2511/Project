package com.myProject.ecommerce.service;

import com.myProject.ecommerce.repo.CustomerRepo;
import com.myProject.ecommerce.dto.Purchase;
import com.myProject.ecommerce.dto.PurchaseResponse;
import com.myProject.ecommerce.entity.Customer;
import com.myProject.ecommerce.entity.Order;
import com.myProject.ecommerce.entity.OrderItem;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepo customerRepo;

    public CheckoutServiceImpl(CustomerRepo customerRepository) {
        this.customerRepo = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // retrieve the order info from dto
        Order order = purchase.getOrder();

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        // populate order with orderItems
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));

        // populate order with billingAddress and shippingAddress
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        // populate customer with order
        Customer customer = purchase.getCustomer();

//        check if this is an existing customer
        String theEmail = customer.getEmail();

        Customer customerFromDB = customerRepo.findByEmail(theEmail);

        if (customerFromDB != null) {
            customer = customerFromDB;
        }


        customer.add(order);

        // save to the database
        customerRepo.save(customer);

        // return a response
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {

        // generate a random UUID number (UUID version-4)
        return UUID.randomUUID().toString();
    }
}









