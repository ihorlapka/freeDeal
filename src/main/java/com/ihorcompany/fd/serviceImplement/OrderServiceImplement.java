package com.ihorcompany.fd.serviceImplement;

import com.ihorcompany.fd.dto.OrderDTO;
import com.ihorcompany.fd.model.Order;
import com.ihorcompany.fd.repository.OrderRepository;
import com.ihorcompany.fd.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImplement implements OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Optional<Order> findByOrderName(String orderName) {
        return orderRepository.findOrderByOrdrername(orderName);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void deleteByOrderName(String orderName) {
        orderRepository.deleteOrderByOrdrername(orderName);
    }

    @Override
    public List<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable).getContent();
    }

    @Override
    public void saveNewOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrdrername(orderDTO.getOrdername());
        order.setPayment(orderDTO.getPayment());
        order.setDayamount(orderDTO.getDayamount());
        order.setWorkersamount(orderDTO.getWorkersamount());
        order.setDescription("No description");
        order.setWorkpicture("No work picture");
        orderRepository.save(order);
    }
}