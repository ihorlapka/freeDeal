package com.ihorcompany.fd.repository;

import com.ihorcompany.fd.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findOrderByOrdername(String ordername);
    void deleteOrderByOrdername(String ordername);
}
