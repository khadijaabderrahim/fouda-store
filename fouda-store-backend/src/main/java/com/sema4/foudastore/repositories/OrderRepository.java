package com.sema4.foudastore.repositories;

import com.sema4.foudastore.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderCustomRepository {

    @Query("select  o from Order  o " + "where o.client.id = :clientId")
    List<Order> findByClientId(@Param("clientId") Long id);
}
