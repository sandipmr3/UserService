package com.sandip.user.service.UserService.services;

import com.sandip.user.service.UserService.entities.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserService {


    User saveUser(User user);
    
    User updateUser(User user);

    List<User> getAllUser();

    User getUser(String userId);


    User getUserByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User getDistinctUserByEmail(String email);

    String deleteUser(String userId);

    // TODO: DELETE
    //TODO: UPDATE

}
