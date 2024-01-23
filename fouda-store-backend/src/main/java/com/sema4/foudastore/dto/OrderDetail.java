package com.sema4.foudastore.dto;

import com.sema4.foudastore.entities.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDetail {

    private Long number;

    private LocalDateTime date;

    private Status status;

    private Double price;
}
