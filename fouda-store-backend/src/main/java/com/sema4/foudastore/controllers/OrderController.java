package com.sema4.foudastore.controllers;

import com.sema4.foudastore.dto.CreateOrderRequest;
import com.sema4.foudastore.dto.SearchOrderRequest;
import com.sema4.foudastore.dto.UpdateOrderStatusRequest;
import com.sema4.foudastore.entities.Order;
import com.sema4.foudastore.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderController extends ExceptionHandling {

    @Autowired
    private OrderService orderService;

    // all orders
    @GetMapping("/")
    ResponseEntity<List<Order>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    //order detail by id
    @GetMapping("/{id}")
    ResponseEntity<Order> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    // add order
    @PostMapping("/")
    ResponseEntity<Order> save(@RequestBody @Valid CreateOrderRequest orderRequest) {
        Order order = orderService.create(orderRequest.getClientId(), orderRequest.getSelectedProducts());
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @PostMapping("/search")
    ResponseEntity<List<Order>> search(@RequestBody SearchOrderRequest searchOrderRequest) throws InterruptedException {
        return ResponseEntity.ok(orderService.searchOrders(searchOrderRequest));
    }

    @PostMapping("/status")
    ResponseEntity<Order> updateOrderStatus(@RequestBody @Valid UpdateOrderStatusRequest request) {
        return ResponseEntity.ok(orderService.updateOrderStatus(request.getOrderId() , request.getStatus()));
    }

}
