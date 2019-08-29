package com.ihorcompany.fd.control;

import com.ihorcompany.fd.dto.UserDTO;
import com.ihorcompany.fd.exception.StorageFileNotFoundException;
import com.ihorcompany.fd.exception.UserNotFoundException;
import com.ihorcompany.fd.model.User;
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
    private int TOTAL = 5;


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



    @GetMapping("/profile")
    public String profile(Principal principal, Model model,
                          @RequestParam(name = "page", required = false, defaultValue = "1") String page,
                          @RequestParam(name = "sort", required = false, defaultValue = "id") String sort){
        model.addAttribute("user", userService.readByUsername(principal.getName()).orElseThrow(UserNotFoundException::new));
        model.addAttribute("files", storageService.loadAll()
                .map(path -> MvcUriComponentsBuilder.fromMethodName(ProfController.class,
                        "serveFile", path.getFileName().toString()).build().toString()).collect(Collectors.toList()));
        model.addAttribute("orders", orderService.findAll(PageRequest.of(Integer.parseInt(page)-1, TOTAL, Sort.by(sort))));
        System.out.println(userService.readByUsername(principal.getName()));
        return "profile";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute(name = "user") UserDTO user, Principal principal){
        user.setUsername(principal.getName());
        userService.update(user);
        return "redirect:/profile";
    }

    @GetMapping("/profile/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename){
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment: filename=\""+file.getFilename()+"\"").body(file);
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes){
        storageService.store(file);
        redirectAttributes.addFlashAttribute("message", "Your file "+file.getOriginalFilename()+" " +
                "is successfully uploaded");
        return "redirect:/profile";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException e){
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/addFriend")
    public String addFriend(@RequestParam(value = "u", required = false)User user , Principal principal){
        userService.readByUsername(principal.getName()).orElseThrow(UserNotFoundException::new).addUser(user);
        return "redirect:/index";
    }
}
