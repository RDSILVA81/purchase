package com.adentis.purchase.controller;

import com.adentis.purchase.model.Customer;
import com.adentis.purchase.repository.CustomerRepository;
import com.adentis.purchase.repository.OrderRepository;
import com.adentis.purchase.repository.PurchaseRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/purchase")
@Api(value="All apis about purchases and customers")
public class ApplicationController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository order;

    @Autowired
    private PurchaseRepository purchaseRepository;

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

    @PostMapping("/AgeProductsByPurchaseInterval")
    @ApiOperation(value="Returns the number of orders by a period of age products")
    public Map<String,Integer> getAgeProductsByPurchaseInterval(
            @ApiParam(value = "List of dates {\"Oldest\": \"01/01/2020\", \"Newest\": \"30/06/2020\"}", required = true)
            @RequestBody Map<String,String> body){

        try {

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            purchaseRepository.getListOrdersByPeriod(formatter.parse(body.get("Oldest")), formatter.parse(body.get("Newest")))
                    .forEach(purchaseOrder -> System.out.println(purchaseOrder));

        } catch (ParseException e) {
            //Add log4j
            e.printStackTrace();
        }

        return null;
    }


}
