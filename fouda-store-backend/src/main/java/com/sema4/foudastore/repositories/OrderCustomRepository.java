package com.sema4.foudastore.repositories;

import com.sema4.foudastore.entities.Order;
import com.sema4.foudastore.entities.Status;

import java.util.List;

public interface OrderCustomRepository {
    List<Order> searchOrders(Long orderId, Long clientId, Status orderStatus);
}
