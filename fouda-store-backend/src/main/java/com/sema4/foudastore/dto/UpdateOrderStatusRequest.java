package com.sema4.foudastore.dto;

import com.sema4.foudastore.entities.Status;
import lombok.Data;

@Data
public class UpdateOrderStatusRequest {

    private Long orderId;
    private Status status;
}
