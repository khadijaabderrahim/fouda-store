package com.sema4.foudastore.services;

import com.sema4.foudastore.dto.OrderDetail;
import com.sema4.foudastore.dto.SearchOrderRequest;
import com.sema4.foudastore.entities.Client;
import com.sema4.foudastore.entities.Order;
import com.sema4.foudastore.entities.Product;
import com.sema4.foudastore.entities.Status;
import com.sema4.foudastore.exceptions.ElementNotFoundException;
import com.sema4.foudastore.repositories.ClientRepository;
import com.sema4.foudastore.repositories.OrderRepository;
import com.sema4.foudastore.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

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
    public void create(Long clientId, List<Long> productsIds) {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());

        Client client = clientRepository.findById(clientId).orElseThrow();
        order.setClient(client);

        order.setStatus(Status.NEW);

        List<Product> productList = productRepository.findByIds(productsIds);
        order.setProductList(productList);
        this.orderRepository.save(order);
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
