package com.adentis.purchase.controller;

import com.adentis.purchase.model.Customer;
import com.adentis.purchase.model.PurchaseOrder;
import com.adentis.purchase.repository.CustomerRepository;
import com.adentis.purchase.repository.OrderProductRepository;
import com.adentis.purchase.repository.OrderRepository;
import com.adentis.purchase.repository.PurchaseRepository;
import com.adentis.purchase.util.ValidateRange;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/purchase")
@Api( value="All apis about purchases and customers")
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
            @ApiParam(value = "List of ranges {\"range1\": \"1-3\", \"range2\": \"4-6\",\"range3\": \"7-12\" ,\"range4\": \">12\"}", required = true)
            @RequestBody Map<String,String> body){
        return getProductsByRange(body);
    }

    private Map<String,Integer> getProductsByRange(Map<String,String> body){
        Map<String,Integer> mapAll = getMap(getAllPurchases());
        Map<String,Integer> result = new HashMap<>();
        body.forEach((k,v) -> {
            if(mapAll.containsKey(v)){
                result.put(v,mapAll.get(v));
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
                                result.compute(
                                        ValidateRange.getRange(
                                            Period.between(item.getCreationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                                                           LocalDate.now()).toTotalMonths()),
                                        (k, v) -> v + 1);
                            }
                    );
                }
        );
        return result;
    }

    @PostMapping("/AllProductsByRangeFuture")
    @ApiOperation(value="Returns the number of orders by range in months")
    public Stream<Map<String,Integer>>  getAllProductsByRangeFuture(
            @ApiParam(value = "List of ranges {\"range1\": \"1-3\", \"range2\": \"4-6\",\"range3\": \"7-12\" ,\"range4\": \">12\"}", required = true)
            @RequestBody Map<String,String> body){
        CompletableFuture<Map<String,Integer>> [] listFuture = new CompletableFuture[body.size()];
        int count = 0;
        for (Map.Entry<String, String> range : body.entrySet()) {
            listFuture[count++] = CompletableFuture.supplyAsync(()->getProductsRange(range.getValue()));
        }
        return Stream.of(listFuture).map(CompletableFuture::join);
    }

    @Async
    private Map<String,Integer> getProductsRange(String range){
        return getMapFuture(getAllPurchases(),range);
    }


    private Map<String,Integer> getMapFuture(List<PurchaseOrder> orders, String range){
        Map<String,Integer> mapRange = new HashMap<String,Integer>();
        mapRange.put(range,0);
        orders.forEach(
                p -> {
                    p.getItems().forEach(item -> {
                                if (range.equals(ValidateRange.getRange(
                                        Period.between(item.getCreationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                                                LocalDate.now()).toTotalMonths()))) {
                                    mapRange.compute(range,(k, v) -> v + 1 );
                                }
                            }
                    );
                }
        );
        return mapRange;
    }

    private List<PurchaseOrder> getAllPurchases(){
        List<PurchaseOrder> orders =  purchaseRepository.getAllListOrders();
        orderProductRepository.getProductByListOrders(orders);
        return orders;
    }

    private List<PurchaseOrder> getPurchasesByDate(String start, String end){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        List<PurchaseOrder> orders = null;
        try {
            orders = purchaseRepository.getListOrdersByPeriod(formatter.parse(start),formatter.parse(end));
            orderProductRepository.getProductByListOrders(orders);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return orders;
    }
}