package com.sema4.foudastore.services;

import com.sema4.foudastore.dto.SearchOrderRequest;
import com.sema4.foudastore.entities.Order;
import com.sema4.foudastore.exceptions.ElementNotFoundException;
import com.sema4.foudastore.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent())
            return order.get();
        throw new ElementNotFoundException("order " + id + " not found");
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<Order> searchOrders(SearchOrderRequest searchOrderRequest) {
        return orderRepository.searchOrders(searchOrderRequest.getOrderId(),searchOrderRequest.getClientId(),searchOrderRequest.getOrderStatus());
    }


}
