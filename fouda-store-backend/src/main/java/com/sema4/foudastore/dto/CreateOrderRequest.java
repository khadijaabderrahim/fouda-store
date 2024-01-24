package com.sema4.foudastore.dto;


import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {
    private Long clientId;
    private List<Long> selectedProducts;
}
