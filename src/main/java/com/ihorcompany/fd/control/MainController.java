package com.ihorcompany.fd.control;
import com.ihorcompany.fd.dto.OrderDTO;
import com.ihorcompany.fd.dto.UserDTO;
import com.ihorcompany.fd.model.User;
import com.ihorcompany.fd.service.OrderService;
import com.ihorcompany.fd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class MainController {

    private UserService userService;
    private OrderService orderService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping({"/", "/index", "/logout"})
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String login(@RequestParam(name = "error", defaultValue = "false") String hasError, Model model){
        if(Boolean.parseBoolean(hasError))
        model.addAttribute("error", "Invalid username or password");
        return "profile";
    }

    @GetMapping("/registration")
    public String register(Model model){
        model.addAttribute("userDTO", new UserDTO());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute(name = "userDTO") @Valid UserDTO userDTO, BindingResult result,
                           WebRequest webRequest, Errors errors, Model model){
        if (result.hasErrors()){
            model.addAttribute("userDTO", userDTO);
            return "/register";
        }
        userService.registerNewUser(userDTO);
        return "redirect:/profile";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model){
        model.addAttribute("username", principal.getName());
        System.out.println(userService.readByUsername(principal.getName()));
        return "profile";
    }

    @PostMapping("/myProfile")
    public String  myProfile(@ModelAttribute(name = "user")User user, Model model){
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/orderPage")
    public String orderPage(Principal principal, Model model){
        model.addAttribute("ordername", principal.getName());
        model.addAttribute("orderDTO", new OrderDTO());
        return "order";
    }

    @PostMapping("/order")
    public String order(@ModelAttribute(name = "orderDTO")OrderDTO orderDTO, Model model){
        model.addAttribute("orderDTO", orderDTO);
        orderService.saveNewOrder(orderDTO);
        return "order";
    }
}
