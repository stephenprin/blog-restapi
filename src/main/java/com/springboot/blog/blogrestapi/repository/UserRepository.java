package com.springboot.blog.blogrestapi.repository;

import com.springboot.blog.blogrestapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existByEmail(String email);


    Optional<Object> findByUsernameOrEmail();
}
