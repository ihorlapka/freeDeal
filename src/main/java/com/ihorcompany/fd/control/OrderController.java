package com.ihorcompany.fd.control;

import com.ihorcompany.fd.exception.OrderNotFoundException;
import com.ihorcompany.fd.exception.UserNotFoundException;
import com.ihorcompany.fd.model.Order;
import com.ihorcompany.fd.model.OrderStatus;
import com.ihorcompany.fd.model.User;
import com.ihorcompany.fd.service.MailService;
import com.ihorcompany.fd.service.OrderService;
import com.ihorcompany.fd.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Controller
public class OrderController {

    private OrderService orderService;
    private UserService userService;
    private MailService mailService;
    private static final String MAIL_TEXT = "Hi! %s, user %s has just accepted to execute your order: %s, for %d days.\n" +
            "Good luck!";
    private static final String DONE_TEXT = "Hi! %s, user %s has order: \"%s\" done, please check!";

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
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

    @PostMapping("/executeOrder/{id}")
    public String execute(@PathVariable(name = "id") Order order, Principal principal){
        User user = userService.readByUsername(principal.getName()).orElseThrow(UserNotFoundException::new);
        System.out.println("\n"+user.getUsername()+" is going to commit "+order.getOrdername()+"\n");
        if (order.getStatus() == OrderStatus.ACTIVE){
            userService.create(
                    userService.readByUsername(user.getUsername()).map(u-> {
                        u.getOrdersExecuting().add(order);
                        order.setStatus(OrderStatus.PROCESSING);
                        order.setExecutingUser(user);
                        orderService.saveOrder(order);
                        return u;
                    }).orElseThrow(UserNotFoundException::new));
            System.out.println("\nmail to "+order.getUser().getUsername()+" is going to be sent on "+order.getUser().getEmail());
            mailService.send("Your order is going to be executed",
                    order.getUser().getEmail(),
                    String.format(MAIL_TEXT, order.getUser().getUsername(), principal.getName(),
                            order.getOrdername(), order.getDayamount()));
            System.out.println("\nmail to "+order.getUser().getUsername()+" successfully sent!");
        }
        else {
            System.out.println("\nOrder is busy!\n");

        }
        return "redirect:/index";
    }

    @PostMapping("/orderDone/{id}")
    public String  oderDone(@PathVariable(value = "id") Order order, Principal principal){
        System.out.println("\nNow order "+order.getOrdername()+" is accomplished!\n");
        order.setStatus(OrderStatus.ACCOMPLISHED);
        orderService.saveOrder(order);
        mailService.send("Your order has already done, Check it out!",
                order.getUser().getEmail(),
                String.format(DONE_TEXT, order.getUser().getUsername(), principal.getName(),
                        order.getOrdername()));
        return "redirect:/profile";
    }
}
