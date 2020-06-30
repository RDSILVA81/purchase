package com.adentis.purchase.controller;

import com.adentis.purchase.model.Customer;
import com.adentis.purchase.model.PurchaseOrder;
import com.adentis.purchase.repository.CustomerRepository;
import com.adentis.purchase.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class ApplicationController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository order;

    @RequestMapping("/AllCustomer")
    public List<Customer> getListCostumer(){
        return customerRepository.findAll();
    }
    @RequestMapping("/OrderByCustomerName")
    public Customer getOrdersByCustomer(@RequestParam String name){
        Customer customer = customerRepository.findByName(name);
        order.getOrderByCustomer(customer);
        return customer;
    }


}
