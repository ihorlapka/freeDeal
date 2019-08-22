package com.ihorcompany.fd.control;

import com.ihorcompany.fd.exception.UserNotFoundException;
import com.ihorcompany.fd.model.User;
import com.ihorcompany.fd.repository.UserRepository;
import com.ihorcompany.fd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class ProfController {

    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model){
        model.addAttribute("user", userService.readByUsername(principal.getName()).orElseThrow(UserNotFoundException::new));
        System.out.println(userService.readByUsername(principal.getName()));
        return "profile";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute(name = "user") User user){
        userService.update(user);
        return "redirect:/profile";
    }
}
