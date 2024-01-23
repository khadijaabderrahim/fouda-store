package com.sema4.foudastore.dto;

import com.sema4.foudastore.entities.Client;
import com.sema4.foudastore.entities.Order;
import com.sema4.foudastore.entities.Status;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ClientDetailResponse {

    private Client client;

    List<OrderDetail> orders;



}
