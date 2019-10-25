package ru.itis.chats.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.chats.dto.TokenDto;
import ru.itis.chats.forms.LoginForm;
import ru.itis.chats.forms.RegForm;
import ru.itis.chats.model.Message;
import ru.itis.chats.model.Role;
import ru.itis.chats.model.Token;
import ru.itis.chats.model.User;
import ru.itis.chats.rep.MesRep;
import ru.itis.chats.rep.TokenRep;
import ru.itis.chats.rep.UserRep;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Lazy
@Service
public class UserServiceImpl implements UserService {

    @Lazy
    @Autowired
    private UserRep userRep;


    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenRep tokenRep;
    @Autowired
    private MesRep mesRep;

    @Autowired
    private TokenServiceImpl tokenServ;
    private Integer expiredTime = 3600;

    @Override
    public Long findIdByEmail(String email) {
        return userRep.findByEmail(email).get().getId();
    }

    @Override
    public TokenDto login(LoginForm loginForm) {
        Optional<User> userCandidate = userRep.findFirstByEmail(loginForm.getEmail());
        if (userCandidate.isPresent()) {
            User user = userCandidate.get();
            if (passwordEncoder.matches(loginForm.getPassword(), user.getHash())) {
                if (tokenServ.findFirstByUser(user.getEmail()).isPresent()) {
                    Token tok = tokenServ.findFirstByUser(user.getEmail()).get();
                    return TokenDto.from(tok.getValue());
                } else {
                    String value = UUID.randomUUID().toString();
                    Token token = Token.builder()
                            .createdAt(LocalDateTime.now())
                            .expiredDateTime(LocalDateTime.now().plusSeconds(expiredTime))
                            .value(value)
                            .user(user)
                            .build();
                    System.out.println(LocalDateTime.now() + ":now" + "    not now:" + LocalDateTime.now().plusSeconds(expiredTime));
                    tokenRep.save(token);
                    return TokenDto.from(value);
                }
            }
        }
        throw new BadCredentialsException("Incorrect login or password");
    }


    @Override
    public void register(RegForm regForm) {
        User user = User.builder()
                .username(regForm.getUserName())
                .email(regForm.getEmail())
                .hash(passwordEncoder.encode(regForm.getPassword()))
                .role(Role.USER)
                .build();
        userRep.save(user);
    }

    @Override
    public void putMessage(Long userId, String message) {
        Message mes = Message.builder()
                .message(message)
                .user(userRep.findById(userId).get())
                .build();
        mesRep.save(mes);
    }
}