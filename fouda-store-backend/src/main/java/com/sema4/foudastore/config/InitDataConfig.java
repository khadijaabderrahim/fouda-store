package com.sema4.foudastore.config;

import com.sema4.foudastore.entities.Client;
import com.sema4.foudastore.entities.Order;
import com.sema4.foudastore.entities.Product;
import com.sema4.foudastore.entities.Status;
import com.sema4.foudastore.repositories.ClientRepository;
import com.sema4.foudastore.repositories.OrderRepository;
import com.sema4.foudastore.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(InitDataConfig.class);
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
        Client c5 = new Client(null,"Abderrahim","ABDERRAHIM","amani.abderrahim85@gmail.com");
        Client c6 = new Client(null,"Atef","ABDERRAHIM","amani.abderrahim85@gmail.com");
        Client c7 = new Client(null,"Mabrouk","ABDERRAHIM","amani.abderrahim85@gmail.com");
        Client c8 = new Client(null,"Mahdi","ABDERRAHIM","amani.abderrahim85@gmail.com");
        clientRepository.save(c1);
        clientRepository.save(c2);
        clientRepository.save(c3);
        clientRepository.save(c4);
        clientRepository.save(c5);
        clientRepository.save(c6);
        clientRepository.save(c7);
        clientRepository.save(c8);
    }

    private void initProducts() {
        Product p1 = new Product(null,"biberon","biberon avent 250ml",11.99,8,"https://www.cdiscount.com/pdt2/8/8/8/1/700x700/auc8710103875888/rw/biberon-avent-natural-260-ml-trasparente-scf033-17.jpg");
        Product p2 = new Product(null,"biberon","biberon avent 180ml",9.99,7,"https://images.philips.com/is/image/philipsconsumer/71078110e4634438b495ad7400c3fda5?wid=420&hei=360&$jpglarge$");
        Product p3 = new Product(null,"tétine","tétine avent 18 mois",4.0,6,"https://m.media-amazon.com/images/I/51Zb0ITj6XL._AC_SL1500_.jpg");
        Product p4 = new Product(null,"couche","couche pampers taille 4",10.2,9,"https://m.media-amazon.com/images/I/81lWODVmLsL.__AC_SX300_SY300_QL70_ML2_.jpg");
        Product p5 = new Product(null,"couche","couche pampers taille 5",11.0,9,"https://m.media-amazon.com/images/I/81lWODVmLsL.__AC_SX300_SY300_QL70_ML2_.jpg");
        Product p6 = new Product(null,"couche","couche pampers taille 1",9.0,9,"https://m.media-amazon.com/images/I/81lWODVmLsL.__AC_SX300_SY300_QL70_ML2_.jpg");
        Product p7 = new Product(null,"couche","couche pampers taille 2",10.0,9,"https://m.media-amazon.com/images/I/81lWODVmLsL.__AC_SX300_SY300_QL70_ML2_.jpg");
        Product p8 = new Product(null,"couche","couche pampers taille 3",10.1,9,"https://m.media-amazon.com/images/I/81lWODVmLsL.__AC_SX300_SY300_QL70_ML2_.jpg");
        Product p9 = new Product(null,"vélo","vélo 18 m+",40.0,7,"https://media.larmoiredebebe.com/ab/products/00/06/04/77/AB0006047758.jpg");
        Product p10 = new Product(null,"trottinette","trottinette glober up rose",100.0,9,"https://static.fnac-static.com/multimedia/Images/FR/MDM/e1/06/01/16844513/3756-1/tsp20231111172030/Trottinette-enfant-Globber-Go-Up-Foldable-T-Bar-Rose-et-Noir.jpg");
        Product p11 = new Product(null,"Poussette","Poussette canne noir dossier",100.0,2,"https://media.carrefour.fr/medias/6015026e072c3c779a85624f4a0e3dc9/p_1500x1500/3616181074678-photosite-20210108-175506-0.jpg");
        Product p12 = new Product(null,"Poussette","Poussette CASSY 6-48 mois",100.0,2,"https://m.media-amazon.com/images/I/81fcG+eD7QL._AC_SL1500_.jpg");
        Product p13 = new Product(null,"Poussette","Lara²",250.0,8,"https://images.maxi-cosi.fr/dorel-public-storage-prod/catalog/product/cache/655f0b719d7fda71d9d06b11a51a9714/1/2/1233750112_2021_maxicosi_stroller_lara2_essentialgraphite_3qrtleft.png");

        productRepository.save(p1);
        productRepository.save(p2);
        productRepository.save(p3);
        productRepository.save(p4);
        productRepository.save(p5);
        productRepository.save(p6);
        productRepository.save(p7);
        productRepository.save(p8);
        productRepository.save(p9);
        productRepository.save(p10);
        productRepository.save(p10);
        productRepository.save(p11);
        productRepository.save(p12);
        productRepository.save(p13);

    }

    private void initOrders() {
        Random random = new Random();
        int ordersCount = 10;
        LOGGER.info("Init {} orders .." , ordersCount);
        for(int i = 1 ;  i<=ordersCount ; i++){
            Client client = clientRepository.findById(random.nextLong(8) + 1).get();
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
