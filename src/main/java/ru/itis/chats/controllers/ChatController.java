package ru.itis.chats.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.chats.dto.MessageDto;
import ru.itis.chats.model.Message;
import ru.itis.chats.model.Token;
import ru.itis.chats.serv.MessageServiceImpl;
import ru.itis.chats.serv.TokenServiceImpl;

import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private TokenServiceImpl tokenServ;

    @Autowired
    private MessageServiceImpl mesServ;

    @GetMapping("/chat/{user}")
    public List<MessageDto> getChatPage(@PathVariable("user") String user) {
        System.out.println("Chat");
        Token token = tokenServ.findFirstByUser(user).get();
        List<MessageDto> list = null;
        if (tokenServ.isNotExpired(token.getValue())) {
            System.out.println(mesServ.getAllMessages().toArray().length + " LENGTH");
            list = mesServ.getAllMessages();
        }
        return list;
    }
}