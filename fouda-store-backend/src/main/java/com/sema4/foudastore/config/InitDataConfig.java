package com.sema4.foudastore.config;

import com.sema4.foudastore.entities.Client;
import com.sema4.foudastore.entities.Order;
import com.sema4.foudastore.entities.Product;
import com.sema4.foudastore.entities.Status;
import com.sema4.foudastore.repositories.ClientRepository;
import com.sema4.foudastore.repositories.OrderRepository;
import com.sema4.foudastore.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class InitDataConfig {

    //init data
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    private void initClients() {
        Client c1 = new Client(null,"Khadija","ABDERRAHIM","khadija.abderrahim1991@gmail.com");
        Client c2 = new Client(null,"Zied","AYADI","zied.ayedi22@gmail.com");
        Client c3 = new Client(null,"Fedi","AYADI","fedi.ayadi22@gmail.com");
        Client c4 = new Client(null,"Amani","ABDERRAHIM","amani.abderrahim85@gmail.com");
        clientRepository.save(c1);
        clientRepository.save(c2);
        clientRepository.save(c3);
        clientRepository.save(c4);
    }

    private void initProducts() {
        Product p1 = new Product(null,"biberon","biberon avent 250ml",11.99);
        Product p2 = new Product(null,"biberon","biberon avent 180ml",9.99);
        Product p3 = new Product(null,"tétine","tétine avent 18 mois",4.0);
        Product p4 = new Product(null,"couche","couche pampers taille 4",10.2);

        productRepository.save(p1);
        productRepository.save(p2);
        productRepository.save(p3);
        productRepository.save(p4);

    }

    private void initOrders() {
        Random random = new Random();
        for(int i = 1 ;  i<=1000 ; i++){
            Client client = clientRepository.findById(random.nextLong(4) + 1).get();
            int numberOfProduct = random.nextInt(11) + 1;
            List<Product> products = new ArrayList<>();
            for(int j = 1 ; j <= numberOfProduct ; j++) {
                products.add(productRepository.findAll().get(random.nextInt(4)));
            }
            Order order = new Order(null, client, products, randomLocalDateTime(), Status.values()[random.nextInt(4)]);
            orderRepository.save(order);
        }
    }

    @Bean
    public void initData() {
        initClients();
        initProducts();
        initOrders();

    }

    private static LocalDateTime randomLocalDateTime() {
        long minDay = LocalDate.of(2020, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2023, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay).atTime(ThreadLocalRandom.current().nextInt(23) , ThreadLocalRandom.current().nextInt(59));
    }
}
