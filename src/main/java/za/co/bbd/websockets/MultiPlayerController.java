package za.co.bbd.websockets;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;


@Controller
public class MultiPlayerController {

    @MessageMapping("/time")
    @SendTo("/topic/multiplayer")
    public MessageContent greeting(MessageContent message) throws Exception {
        return message;
    }
}
