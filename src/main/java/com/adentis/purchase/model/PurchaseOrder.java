package com.adentis.purchase.model;

import lombok.*;
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PurchaseOrder {
    private int id;
    private int idCostumer;
    private Double cost,shipping;
    private Date orderDate;
    private @Nullable  List<Product> items;
}
