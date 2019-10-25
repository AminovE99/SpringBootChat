package ru.itis.chats.forms;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Component
@Lazy
public class LoginForm {
    private String email;
    private String password;
}