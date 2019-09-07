package com.ihorcompany.fd.control;

import com.ihorcompany.fd.dto.UserDTO;
import com.ihorcompany.fd.exception.StorageFileNotFoundException;
import com.ihorcompany.fd.exception.UserNotFoundException;
import com.ihorcompany.fd.model.Message;
import com.ihorcompany.fd.model.User;
import com.ihorcompany.fd.service.MessageService;
import com.ihorcompany.fd.service.OrderService;
import com.ihorcompany.fd.service.StorageService;
import com.ihorcompany.fd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.stream.Collectors;

@Controller
public class ProfController {

    private UserService userService;
    private StorageService storageService;
    private OrderService orderService;
    private MessageService messageService;
    private int TOTAL = 5;
//    private String TEXT;


    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setStorageService(StorageService storageService) {
        this.storageService = storageService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model,
                          @RequestParam(name = "pageMyOrder", required = false, defaultValue = "1") String pageMyOrder,
                          @RequestParam(name = "sortMyOrder", required = false, defaultValue = "id") String sortMyOrder,
                          @RequestParam(name = "pageExecOrder", required = false, defaultValue = "1") String pageExecOrder,
                          @RequestParam(name = "sortExecOrder", required = false, defaultValue = "id") String sortExecOrder) {
        model.addAttribute("user", userService.readByUsername(principal.getName()).orElseThrow(UserNotFoundException::new));
        model.addAttribute("files", storageService.loadAll()
                .map(path -> MvcUriComponentsBuilder.fromMethodName(ProfController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));
        model.addAttribute("orders", orderService.findAll(PageRequest.of(Integer.parseInt(pageMyOrder) - 1,
                TOTAL, Sort.by(sortMyOrder))));
        model.addAttribute("orders", orderService.findAll(PageRequest.of(Integer.parseInt(pageExecOrder) - 1,
                TOTAL, Sort.by(sortExecOrder))));
        System.out.println("\n"+userService.readByUsername(principal.getName())+"\n");
        return "profile";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute(name = "user") UserDTO user, Principal principal) {
        user.setUsername(principal.getName());
        userService.update(user);
        return "redirect:/profile";
    }

    @GetMapping("/profile/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment: filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        storageService.store(file);
        redirectAttributes.addFlashAttribute("message", "Your file " + file.getOriginalFilename() + " " +
                "is successfully uploaded");
        return "redirect:/profile";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException e) {
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/addFriend/{id}")
    public String addFriend(@PathVariable(value = "id") User user, Principal principal) {
        System.out.println("\nUserId = " + user.getId() + " Username = " + user.getUsername() +
                ", added to " + principal.getName() + " friends\n");
        userService.create(
                userService.readByUsername(principal.getName()).map(u -> {
                            u.getFriends().add(user);
                            user.getFriends().add(u);
                            return u;
                        }).orElseThrow(UserNotFoundException::new));
        return "redirect:/index";
    }

    @PostMapping("/sendMessage/{id}")
    public String sendMessage(@PathVariable (value = "id") User user, Principal principal, Model model){
        System.out.println("\n"+principal.getName()+" is trying to send message to "+user.getUsername()+"\n");
        Message message = new Message();
        model.addAttribute("message", message);
        messageService.send(message,
                userService.readByUsername(principal.getName()).get(),
                userService.readByUsername(user.getUsername()).get());
        message.setMessage("test!");
        messageService.save(message);
        System.out.println(principal.getName()+" send message to "+user.getUsername());
        return "redirect:/profile";
    }

    @PostMapping("/deleteFriend/{id}")
    public String deleteFriend(@PathVariable(value = "id") User user, Principal principal){
        System.out.println("\n"+user.getUsername()+" is deleted from "+principal.getName()+"'s friends");
        userService.create(userService.readByUsername(principal.getName()).map(u -> {
                    u.getFriends().remove(user);
                    user.getFriends().remove(u);
                    return u;
                }).orElseThrow(UserNotFoundException::new));
        return "redirect:/profile";
    }
}
