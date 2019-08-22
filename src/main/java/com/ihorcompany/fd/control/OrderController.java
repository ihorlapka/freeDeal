package com.ihorcompany.fd.control;

import com.ihorcompany.fd.dto.OrderDTO;
import com.ihorcompany.fd.model.Order;
import com.ihorcompany.fd.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class OrderController {

    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orderPage")
    public String orderPage(Principal principal, Model model){
        OrderDTO orderDTO = new OrderDTO();
        model.addAttribute("ordername", principal.getName());
        model.addAttribute("orderDTO", orderDTO);
        return "order";
    }

    @PostMapping("/order")
    public String order(@ModelAttribute(name = "orderDTO")OrderDTO orderDTO){
        orderService.saveNewOrder(orderDTO);
        System.out.println("Order "+orderDTO+" successfully created");
        return "index";
    }
}
