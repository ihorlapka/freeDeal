package com.ihorcompany.fd.control;

import com.ihorcompany.fd.dto.UserDTO;
import com.ihorcompany.fd.exception.UserNotFoundException;
import com.ihorcompany.fd.service.OrderService;
import com.ihorcompany.fd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    private int TOTAL = 10;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping({"/", "/index", "/logout"})
    public String index(Model model, Principal principal,
                        @RequestParam(name = "userPage", required = false, defaultValue = "1") String userPage,
                        @RequestParam(name = "userSort", required = false, defaultValue = "username") String userSort,
                        @RequestParam(name = "orderPage", required = false, defaultValue = "1") String orderPage,
                        @RequestParam(name = "orderSort", required = false, defaultValue = "ordername") String orderSort){
        model.addAttribute("users", userService.findAll(PageRequest.of(
                Integer.parseInt(userPage)-1, TOTAL, Sort.by(userSort))));
        model.addAttribute("orders", orderService.findAll(PageRequest.of(
                Integer.parseInt(orderPage)-1, TOTAL, Sort.by(orderSort))));
        if(principal != null)
            model.addAttribute("user", userService.readByUsername(principal.getName()).orElseThrow(UserNotFoundException::new));
        return "index";
    }

    @PostMapping("/loginProcessing")
    public String loginPage(@RequestParam(name = "error", defaultValue = "false") String hasError, Model model){
        System.out.println(Boolean.parseBoolean(hasError));
        if(Boolean.parseBoolean(hasError)){
            System.out.println("error");
            model.addAttribute("error", "Invalid username or password");
        }
        return "index";
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
        return "index";
    }

}
