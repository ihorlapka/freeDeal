package com.ihorcompany.fd.service;

import com.ihorcompany.fd.dto.OrderDTO;
import com.ihorcompany.fd.model.Order;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Optional<Order> findById(Long id);
    Optional<Order> findByOrderName(String orderName);
    List<Order> findAll();
    void saveNewOrder(OrderDTO orderDTO);
    void deleteById(Long id);
    void deleteByOrderName(String orderName);
    List<Order> findAll(Pageable pageable);

}
