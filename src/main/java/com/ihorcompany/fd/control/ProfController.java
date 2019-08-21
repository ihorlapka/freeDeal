package com.ihorcompany.fd.control;

import com.ihorcompany.fd.model.User;
import com.ihorcompany.fd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class ProfController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model){
        model.addAttribute("username", principal.getName());
        System.out.println(userService.readByUsername(principal.getName()));
        return "profile";
    }

    @PostMapping("/myProfile")
    public String  myProfile(@ModelAttribute(name = "user") User user, Model model,
                             BindingResult result){
        if (result.hasErrors()){
            return "index";
        }
        model.addAttribute("user", user);
        return "profile";
    }
}
