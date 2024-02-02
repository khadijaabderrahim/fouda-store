package com.sema4.foudastore.services;

import com.sema4.foudastore.dto.SearchOrderRequest;
import com.sema4.foudastore.entities.Order;
import com.sema4.foudastore.entities.Status;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> findAll();
    Order findById(Long id);

    Order create(Long clientId, List<Long> productsIds);

    void deleteById(Long id);

    List<Order> searchOrders(SearchOrderRequest searchOrderRequest);

    Order updateOrderStatus(Long orderId, Status status);
}
