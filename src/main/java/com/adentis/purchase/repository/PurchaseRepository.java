package com.adentis.purchase.repository;

import com.adentis.purchase.model.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Repository
public class PurchaseRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    public List<PurchaseOrder> getListOrdersByPeriod(Date startDate, Date endDate){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        return jdbc.query("SELECT * FROM PURCHASE_ORDER PO WHERE PO.ORDER_DATE BETWEEN " +
                        "parsedatetime(:startDate, 'dd-MM-yyyy') AND " +
                        "parsedatetime(:endDate, 'dd-MM-yyyy')",
                new MapSqlParameterSource()
                        .addValue("startDate",formatter.format(startDate))
                        .addValue("endDate",formatter.format(endDate)),
                (rs,row) -> new PurchaseOrder(
                        rs.getInt("id"),
                        rs.getInt("id_customer"),
                        rs.getDouble("cost"),
                        rs.getDouble("shipping"),
                        rs.getDate("order_date"),
                        null
                )
        );
    }

    public List<PurchaseOrder> getAllListOrders(){
        return jdbc.query("SELECT * FROM PURCHASE_ORDER PO ",
                (rs,row) -> new PurchaseOrder(
                        rs.getInt("id"),
                        rs.getInt("id_customer"),
                        rs.getDouble("cost"),
                        rs.getDouble("shipping"),
                        rs.getDate("order_date"),
                        null
                )
        );
    }
}
