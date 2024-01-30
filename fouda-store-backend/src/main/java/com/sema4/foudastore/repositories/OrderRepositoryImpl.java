package com.sema4.foudastore.repositories;

import com.sema4.foudastore.entities.Order;
import com.sema4.foudastore.entities.Status;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderCustomRepository {

    @Autowired
    private EntityManager em;

    @Override
    public List<Order> searchOrders(Long orderId, Long clientId, Status orderStatus) {
        String queryString = "Select o  from  Order o where 1 = 1\n";
        if (orderId != null)
            queryString += "and o.id = :orderId \n";
        if (clientId != null)
            queryString += "and o.client.id = :clientId \n";
        if (orderStatus != null)
            queryString += "and o.status = :orderStatus";

        Query query = em.createQuery(queryString, Order.class);
        if (orderId != null)
            query.setParameter("orderId", orderId);
        if (clientId != null)
            query.setParameter("clientId", clientId);
        if (orderStatus != null)
            query.setParameter("orderStatus", orderStatus);
        return query.getResultList();
    }
}
