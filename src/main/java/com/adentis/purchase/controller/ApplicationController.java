package com.adentis.purchase.controller;

import com.adentis.purchase.model.Customer;
import com.adentis.purchase.model.PurchaseOrder;
import com.adentis.purchase.repository.CustomerRepository;
import com.adentis.purchase.repository.OrderProductRepository;
import com.adentis.purchase.repository.OrderRepository;
import com.adentis.purchase.repository.PurchaseRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ValueRange;
import java.util.Date;
import java.util.HashMap;
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

    @Autowired
    private OrderProductRepository orderProductRepository;

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

    @PostMapping("/ProductsByPurchaseInterval")
    @ApiOperation(value="Returns the number of orders by a period")
    public List<PurchaseOrder> getProductsByPurchaseInterval(
            @ApiParam(value = "List of dates {\"Oldest\": \"01/01/2020\", \"Newest\": \"30/06/2020\"}", required = true)
            @RequestBody Map<String,String> body){
        return getPurchasesByDate(body.get("Oldest"),body.get("Newest"));
    }

    @PostMapping("/AgeOfProductsByOrderDate")
    @ApiOperation(value="Returns the number of orders by a period of age products")
    public Map<String,Integer> getAgeOfProductsByOrderDate(
            @ApiParam(value = "List of dates {\"Oldest\": \"01/01/2020\", \"Newest\": \"30/06/2020\"}", required = true)
            @RequestBody Map<String,String> body){

            return getMap(getPurchasesByDate(body.get("Oldest"),body.get("Newest")));
    }

    @PostMapping("/ProductsByRange")
    @ApiOperation(value="Returns the number of orders by range in months")
    public Map<String,Integer> getAllProductsByRange(
            @ApiParam(value = "List of ranges {“1-3”, “4-6”, “7-12”, “>12”}", required = true)
            @RequestBody List<String> body){
        System.out.println(body);
        return null;
    }

    private Map<String,Integer> getProductsByRange(List<String> range){
        Map<String,Integer> mapAll = getMap(getAllPurchases());
        Map<String,Integer> result = new HashMap<>();
        range.forEach(r -> {
            if(mapAll.containsKey(r)){
                result.put(r,mapAll.get(r));
            }
        });
        return result;
    }

    private Map<String,Integer> getMap(List<PurchaseOrder> orders){
        Map<String, Integer> result = new HashMap<>();
        result.put("1-3",0);
        result.put("4-6",0);
        result.put("7-12",0);
        result.put(">12",0);
        orders.forEach(
                p -> {
                    p.getItems().forEach(item -> {
                                result.compute(ValidateRange.getRange(
                                        Period.between(item.getCreationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getMonths()),
                                        (k, v) -> v + 1);
                            }
                    );
                }
        );
        return result;
    }

    private List<PurchaseOrder> getAllPurchases(){
        List<PurchaseOrder> orders =  purchaseRepository.getAllListOrders();
        orderProductRepository.getProductByListorders(orders);
        return orders;
    }

    private List<PurchaseOrder> getPurchasesByDate(String start, String end){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        List<PurchaseOrder> orders = null;
        try {
            orders = purchaseRepository.getListOrdersByPeriod(formatter.parse(start),formatter.parse(end));
            orderProductRepository.getProductByListorders(orders);
        } catch (ParseException e) {
            //Todo add log4j
            e.printStackTrace();
        }
        return orders;
    }
}