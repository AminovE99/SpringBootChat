package ru.itis.chats.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.itis.chats.dto.MessageDto;
import ru.itis.chats.model.Message;
import ru.itis.chats.rep.MesRep;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    @Lazy
    private MesRep mesRep;

    @Override
    public List<MessageDto> getAllMessages() {
        List<MessageDto> list = new ArrayList<>();
        for (Message message : mesRep.getAll()) {
            MessageDto messageDto = MessageDto.builder()
                    .text(message.getMessage())
                    .user(message.getUser().username)
                    .build();
            list.add(messageDto);
        }
        return list;
    }
}
