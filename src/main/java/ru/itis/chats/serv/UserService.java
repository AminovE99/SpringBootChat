package ru.itis.chats.serv;


import ru.itis.chats.dto.TokenDto;
import ru.itis.chats.forms.LoginForm;
import ru.itis.chats.forms.RegForm;

public interface UserService {

    Long findIdByEmail(String email);

    TokenDto login(LoginForm loginForm);

    void register(RegForm regForm);

    void putMessage(Long userId, String message);

}