package com.sema4.foudastore.services;

import com.sema4.foudastore.dto.SearchOrderRequest;
import com.sema4.foudastore.entities.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> findAll();
    Order findById(Long id);

    void save(Order order);

    void deleteById(Long id);

    List<Order> searchOrders(SearchOrderRequest searchOrderRequest);
}
