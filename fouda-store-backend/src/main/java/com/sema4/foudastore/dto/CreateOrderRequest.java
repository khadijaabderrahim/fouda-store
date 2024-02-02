package com.sema4.foudastore.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {

    @NotNull
    private Long clientId;

    @NotNull
    @Size(min = 1,max = 10)
    private List<Long> selectedProducts;
}
