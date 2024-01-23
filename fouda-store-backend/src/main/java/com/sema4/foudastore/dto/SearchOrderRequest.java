package com.sema4.foudastore.dto;

import com.sema4.foudastore.entities.Status;
import lombok.Data;

@Data
public class SearchOrderRequest {
    private Long orderId;
    private Long clientId;

    private Status orderStatus;
}
