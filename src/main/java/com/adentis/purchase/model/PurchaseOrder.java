package com.adentis.purchase.model;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PurchaseOrder {
    private Double cost,shipping;
    private Date orderDate;
}
