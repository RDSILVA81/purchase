package com.adentis.purchase.model;

import lombok.*;
import org.springframework.lang.Nullable;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {
    private int id;
    private String fullName,address,phoneNumber;
    private @Nullable List<PurchaseOrder> purchases;
}
