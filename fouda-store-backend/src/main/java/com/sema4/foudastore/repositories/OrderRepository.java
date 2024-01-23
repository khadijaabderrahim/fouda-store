package com.sema4.foudastore.repositories;

import com.sema4.foudastore.dto.SearchOrderRequest;
import com.sema4.foudastore.entities.Order;
import com.sema4.foudastore.entities.Product;
import com.sema4.foudastore.entities.Status;
import com.sema4.foudastore.exceptions.ElementNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class OrderRepository implements InMemoryRepositoty<Order, Long> {

    private List<Order> orders = new ArrayList<>();

    private Long getNextId() {
        return orders.stream().mapToLong(Order::getId).max().orElse(0L) + 1;
    }

    @Override
    public List<Order> findAll() {
        return orders;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orders.stream().filter(o -> id.equals(o.getId())).findAny();
    }

    @Override
    public void save(Order order) {
        if (order.getId() == null) {
            order.setId(getNextId());
            orders.add(order);
        } else {
            Optional<Order> orderInRepo = orders.stream().filter(o -> order.getId().equals(o.getId())).findAny();
            orderInRepo.ifPresent(value -> orders.remove(value));
            orders.add(order);
        }

    }

    @Override
    public void deleteById(Long id) {
        Optional<Order> order = orders.stream().filter(o -> id.equals(o.getId())).findAny();
        if (order.isPresent()) {
            orders.remove(order.get());
        }
        else{
            throw new ElementNotFoundException("order " + id + " not found");
        }
    }

    public List<Order> findByClientId(Long id) {
        return orders.stream().filter(o -> Objects.equals(o.getClient().getId(), id)).toList();
    }

    public List<Order> searchOrders(Long orderId, Long clientId, Status orderStatus) {
        List<Order> filtredOrders = orders;
        if(orderId != null)
            filtredOrders = filtredOrders.stream().filter(o -> Objects.equals(orderId,o.getId())).toList();
        if(clientId != null)
            filtredOrders = filtredOrders.stream().filter(o -> Objects.equals(clientId,o.getClient().getId())).toList();
        if(orderStatus != null)
            filtredOrders = filtredOrders.stream().filter(o -> Objects.equals(orderStatus,o.getStatus())).toList();
        return  filtredOrders;
    }
}
