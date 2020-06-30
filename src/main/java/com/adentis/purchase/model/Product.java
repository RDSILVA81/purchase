package com.adentis.purchase.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    private String descritption,category;
    private Double price,weight;
    private Date creationDate;
}
