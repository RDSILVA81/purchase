package com.adentis.purchase.repository;

import com.adentis.purchase.model.Customer;
import com.adentis.purchase.model.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    public void getOrderByCustomer(Customer customer){
        customer.setPurchases(
             jdbc.query("SELECT PO.*\n" +
                    "   FROM PURCHASE_ORDER PO JOIN CUSTOMER C  ON PO.ID_CUSTOMER = C.ID\n" +
                    " WHERE C.ID = :id\n", new MapSqlParameterSource("id", customer.getId()),
                    (rs,row)-> new PurchaseOrder(
                            rs.getDouble("cost"),
                            rs.getDouble("shipping"),
                            rs.getDate("order_date")
                    ))
        );
    }

}
