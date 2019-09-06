package com.ihorcompany.fd.control;

import com.ihorcompany.fd.messaging.Greeting;
import com.ihorcompany.fd.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;


@Controller
public class WebSocketController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(Message message, Principal principal) throws Exception {
        Thread.sleep(1000);
        return new Greeting(principal.getName()+": "+HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
