package com.ihorcompany.fd.serviceImplement;

import com.ihorcompany.fd.dto.OrderDTO;
import com.ihorcompany.fd.exception.OrderNotFoundException;
import com.ihorcompany.fd.exception.UserNotFoundException;
import com.ihorcompany.fd.model.Order;
import com.ihorcompany.fd.repository.OrderRepository;
import com.ihorcompany.fd.repository.UserRepository;
import com.ihorcompany.fd.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImplement implements OrderService {

    private OrderRepository orderRepository;
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
        return orderRepository.findOrderByOrdername(orderName);
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
        orderRepository.deleteOrderByOrdername(orderName);
    }

    @Override
    public List<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable).getContent();
    }

    @Override
    public void saveNewOrder(OrderDTO orderDTO) {
        Order order = findByOrderName(orderDTO.getOrdername()).orElseThrow(OrderNotFoundException::new);
        order.setOrdername(orderDTO.getOrdername());
        order.setPayment(orderDTO.getPayment());
        order.setDayamount(orderDTO.getDayamount());
        order.setWorkersamount(orderDTO.getWorkersamount());
        order.setDescription(orderDTO.getDescription());
        order.setWorkpicture("pictures/work.jpg");
        order.setDate(orderDTO.getDate());
        System.out.println(orderDTO.getUsername());
        order.setUser(userRepository.findUserByUsername(orderDTO.getUsername()).orElseThrow(UserNotFoundException::new));
        orderRepository.save(order);
    }

    @Override
    public Order update(Order oldOrder) {
        Order order = findByOrderName(oldOrder.getOrdername()).orElseThrow(OrderNotFoundException::new);
        order.setPayment(oldOrder.getPayment());
        order.setDayamount(oldOrder.getDayamount());
        order.setWorkersamount(oldOrder.getWorkersamount());
        order.setDescription(oldOrder.getDescription());
        order.setWorkpicture(oldOrder.getWorkpicture());
        return orderRepository.save(order);
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}
