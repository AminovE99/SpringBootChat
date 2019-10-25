package ru.itis.chats.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.chats.rep.TokenRep;

import java.time.LocalDateTime;


@Configuration
@EnableScheduling
public class Shedul {

    @Autowired
    private TokenRep tokenRep;

    @Scheduled(cron = "*/10 * * * * *")
    @Transactional
    public void removeTokens(){tokenRep.deleteTokensByExpiredDateTimeBefore(LocalDateTime.now());}
}