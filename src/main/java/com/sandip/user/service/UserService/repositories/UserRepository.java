package com.sandip.user.service.UserService.repositories;

import com.sandip.user.service.UserService.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);

    // Write here custom implementation for execute query.
}
