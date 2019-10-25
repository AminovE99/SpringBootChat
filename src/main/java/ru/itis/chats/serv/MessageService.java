package ru.itis.chats.serv;


import ru.itis.chats.dto.MessageDto;

import java.util.List;

public interface MessageService {
    List<MessageDto> getAllMessages();
}