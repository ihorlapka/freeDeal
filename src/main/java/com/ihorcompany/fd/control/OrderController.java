package com.ihorcompany.fd.control;

import com.ihorcompany.fd.exception.OrderNotFoundException;
import com.ihorcompany.fd.exception.UserNotFoundException;
import com.ihorcompany.fd.model.Order;
import com.ihorcompany.fd.model.User;
import com.ihorcompany.fd.service.OrderService;
import com.ihorcompany.fd.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class OrderController {

    private OrderService orderService;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/orderPage")
    public String orderPage(Principal principal, Model model, @RequestParam(defaultValue = "-1") Long id) {
        model.addAttribute("ordername", principal.getName());
        model.addAttribute("order", orderService.findById(id).orElseGet(Order::new));
        return "order";
    }

    @PostMapping("/order")
    public String order(@ModelAttribute Order newOrder, Principal principal) {
        System.out.println("OrderId = "+newOrder.getId());
        if (newOrder.getId() != null) {
            Order order = orderService.findById(newOrder.getId()).orElseThrow(OrderNotFoundException::new);

            BeanUtils.copyProperties(newOrder, order);
            order.setUser(userService.readByUsername(principal.getName()).orElseThrow(UserNotFoundException::new));
            orderService.saveOrder(order);
        }
        else {
            newOrder.setUser(userService.readByUsername(principal.getName()).orElseThrow(UserNotFoundException::new));
            newOrder.setWorkpicture("pictures/work.jpg");
            orderService.saveOrder(newOrder);
        }
        return "redirect:index";
    }

    @PostMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable Long id){
        System.out.println("\nOrder with Id = "+id+" successfully deleted\n");
        orderService.deleteById(id);
        return "redirect:/profile";
    }


}
