package com.adentis.purchase.repository;

import com.adentis.purchase.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    public List<Customer> findAll(){
        return jdbc.query("select * from customer",
                (rs,row)-> new Customer(
                        rs.getInt("id"),
                        rs.getString("full_Name"),
                        rs.getString("address"),
                        rs.getString("phone_number"),
                        null
                ));
    }

    public Customer findByName(String name){
        return jdbc.queryForObject(
                "Select * from customer where full_name = :name ", new MapSqlParameterSource("name",name),
                (rs,row) -> new Customer(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("address"),
                        rs.getString("phone_number"),
                        null
                )
        );
    }
}
