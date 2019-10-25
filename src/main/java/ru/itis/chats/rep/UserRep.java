package ru.itis.chats.rep;


import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itis.chats.model.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Lazy
@Repository
public interface UserRep extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query(value = "insert into messages values (:user_Id, :message, :time)", nativeQuery = true)
    void putMessage(@Param("user_id") Long user_Id, @Param("message") String message, @Param("time") LocalDateTime time);

    Optional<User> findFirstByEmail(String email);
}