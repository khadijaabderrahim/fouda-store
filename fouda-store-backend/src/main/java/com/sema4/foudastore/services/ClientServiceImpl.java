package com.sema4.foudastore.services;

import com.sema4.foudastore.dto.ClientDetailResponse;
import com.sema4.foudastore.dto.OrderDetail;
import com.sema4.foudastore.entities.Client;
import com.sema4.foudastore.entities.Order;
import com.sema4.foudastore.entities.Product;
import com.sema4.foudastore.exceptions.ElementNotFoundException;
import com.sema4.foudastore.repositories.ClientRepository;
import com.sema4.foudastore.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public ClientDetailResponse findById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            List<Order> orders = orderRepository.findByClientId(id);
            ClientDetailResponse clientDetailResponse = new ClientDetailResponse();
            clientDetailResponse.setClient(client.get());

            List<OrderDetail> orderDetails = orders.stream().map(o -> {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setDate(o.getOrderDate());
                orderDetail.setNumber(o.getId());
                orderDetail.setStatus(o.getStatus());
                orderDetail.setPrice(o.getProductList().stream().mapToDouble(Product::getPrix).sum());
                return orderDetail;
            }).toList();

            clientDetailResponse.setOrders(orderDetails);
            return clientDetailResponse;
        }
        throw new ElementNotFoundException("client " + id + " not found");
    }

    @Override
    public void save(Client client) {
        clientRepository.save(client);
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }
}
