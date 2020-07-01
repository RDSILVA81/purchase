package com.adentis.purchase.repository;

import com.adentis.purchase.model.Product;
import com.adentis.purchase.model.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class OrderProductRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    public void getProductByListorders(List<PurchaseOrder> orders){

        orders.forEach(order ->
                {
                    order.setItems(
                            jdbc.query("SELECT P.* FROM PRODUCT P JOIN ORDER_PRODUCT OP ON P.ID = OP.PRODUCT_ID WHERE OP.ORDER_ID = :orderId ",
                                    new MapSqlParameterSource("orderId", order.getId()),
                                    (rs,row) ->
                                            new Product(
                                                    rs.getString("descritption"),
                                                    rs.getString("category"),
                                                    rs.getDouble("price"),
                                                    rs.getDouble("weight"),
                                                    new Date(rs.getDate("creation_date").getTime())
                                            )
                                    )
                    );
                }
                );

    }

}
