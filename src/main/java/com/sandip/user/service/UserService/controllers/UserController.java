package com.sandip.user.service.UserService.controllers;

import com.sandip.user.service.UserService.entities.User;
import com.sandip.user.service.UserService.services.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    //Create User
    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody User user){

        User user1 = userService.saveUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    // Single user get

    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){

        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);

    }

    // Get All user

    @GetMapping("/allUser")
    public ResponseEntity<List<User>> getAllUser(){

        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);

    }

    @GetMapping("/isUserAuthenticate/{emailId}")
    public ResponseEntity<User> isUserAuthenticated(@PathVariable String emailId){

        System.out.println("Calling controller" + emailId);
        User user = userService.getUserByEmail(emailId);
        return ResponseEntity.ok(user);

    }

/*    @GetMapping("/isUserAuthenticated/{emailId}")
    public ResponseEntity<User> isUserAuthenticatedByEmail(@PathVariable String emailId){

        System.out.println("Calling controller" + emailId);
        User user = userService.getDistinctUserByEmail(emailId);
        return ResponseEntity.ok(user);

    }*/


    // Delete user based on id

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@PathVariable String userId){

        String isDelete = userService.deleteUser(userId);
        return ResponseEntity.ok(isDelete);

    }

    @PersistenceContext
    private EntityManager entityManager;
    @GetMapping("/all")
    public List<String> getAllTableNames() {
        Query query = entityManager.createNativeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema = 'public'");
        return query.getResultList();
    }

}
