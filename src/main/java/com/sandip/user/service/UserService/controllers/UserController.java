package com.sandip.user.service.UserService.controllers;

import com.sandip.user.service.UserService.entities.User;
import com.sandip.user.service.UserService.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "User Controller", tags = {"User"})
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;
    //Create User
    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody User user){

        User user1 = userService.saveUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }
    
    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody User user){

        User updatedUser = userService.updateUser(user);
    	System.out.println("Updated User" + updatedUser.toString());

        return ResponseEntity.status(HttpStatus.CREATED).body(updatedUser);
    	//return null;
    }

    // Single user get

    @ApiOperation(value = "Get User by ID", response = User.class)
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

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable String userId){
    	
		  String isDelete = userService.deleteUser(userId);
		  	Map<String, String> response = new HashMap<>();
	        response.put("status", isDelete);
	        response.put("message", "User deleted successfully");
	        
	      return ResponseEntity.ok(response);	

    }

    @PersistenceContext
    private EntityManager entityManager;
    @GetMapping("/all")
    public List<String> getAllTableNames() {
        Query query = entityManager.createNativeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema = 'public'");
        return query.getResultList();
    }

}
