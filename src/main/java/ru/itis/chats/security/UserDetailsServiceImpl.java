package ru.itis.chats.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.chats.model.Token;
import ru.itis.chats.rep.TokenRep;

import java.util.Optional;

@Lazy
@Service(value = "userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private TokenRep tokensRepository;


    @Override
    public UserDetails loadUserByUsername(String value) throws UsernameNotFoundException {
        Optional<Token> authenticationCandidate = tokensRepository.findFirstByValue(value);
        if (authenticationCandidate.isPresent()) {
            Token token = authenticationCandidate.get();
            return new UserDetailsImpl(token.getUser(), token);
        } return null;
    }
}