package ru.itis.chats.serv;

import ru.itis.chats.model.Token;

import java.util.Optional;

public interface TokenService {

    boolean isNotExpired(String token);

    Optional<Token> findFirstByUser(String email);
}
