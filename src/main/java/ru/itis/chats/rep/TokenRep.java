package ru.itis.chats.rep;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itis.chats.model.Token;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository

public interface TokenRep extends CrudRepository<Token, Long> {
    Optional<Token> findFirstByValue(String value);

    void deleteTokensByExpiredDateTimeBefore(LocalDateTime time);

    Optional<Token> findFirstByUserId(Long id);
}