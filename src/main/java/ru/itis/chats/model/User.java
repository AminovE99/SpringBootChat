package ru.itis.chats.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "users")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    public String username;
    @Column
    private String hash;
    @Column
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Message> messages;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Enumerated(value = EnumType.STRING)
    private Role role=Role.USER;
}
