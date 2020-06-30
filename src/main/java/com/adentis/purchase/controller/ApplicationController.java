package com.adentis.purchase.controller;

import com.adentis.purchase.model.Customer;
import com.adentis.purchase.model.PurchaseOrder;
import com.adentis.purchase.repository.CustomerRepository;
import com.adentis.purchase.repository.OrderRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
@Api(value="All apis about purchases and customers")
public class ApplicationController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository order;

    @GetMapping("/AllCustomer")
    @ApiOperation(value="Returns all customers")
    public List<Customer> getListCostumer(){
        return customerRepository.findAll();
    }

    @GetMapping("/OrderByCustomerName/{name}")
    @ApiOperation(value="Returns all orders made by one customer")
    public Customer getOrdersByCustomer(@PathVariable String name){
        Customer customer = customerRepository.findByName(name);
        order.getOrderByCustomer(customer);
        return customer;
    }


}
