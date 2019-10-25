package ru.itis.chats.serv;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.chats.model.Token;
import ru.itis.chats.rep.TokenRep;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private TokenRep tokenRep;
    @Autowired
    private UserServiceImpl userServ;
    @Override
    public boolean isNotExpired(String token) {
        return LocalDateTime.now().isBefore(tokenRep.findFirstByValue(token).get().getExpiredDateTime());
    }
    @Override
    public Optional<Token> findFirstByUser(String email){
        return tokenRep.findFirstByUserId(userServ.findIdByEmail(email)) ;
    }
}