package com.sandip.user.service.UserService;
import com.sandip.user.service.UserService.controllers.UserController;
import com.sandip.user.service.UserService.entities.Rating;
import com.sandip.user.service.UserService.entities.User;
import com.sandip.user.service.UserService.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceApplicationTests {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    User user;
    List<Rating> ratingList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        ratingList = new ArrayList<>();
        ratingList.add(Rating.builder()
                .ratingId("r1")
                .userId("u1")
                .hotelId("h1")
                .rating(4)
                .feedback("Good service")
                .build());

        user = User.builder()
                .id("u1")
                .name("Sandip")
                .email("sandip@example.com")
                .password("pass123")
                .phonenumber("1234567890")
                .ratingList(ratingList)
                .build();
    }

    @Test
    void testCreateUser() {
        when(userService.saveUser(user)).thenReturn(user);

        ResponseEntity response = userController.createUser(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).saveUser(user);
    }

    @Test
    void testGetSingleUser() {
        when(userService.getUser("u1")).thenReturn(user);

        ResponseEntity<User> response = userController.getSingleUser("u1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).getUser("u1");
    }

    @Test
    void testGetAllUser() {
        List<User> userList = new ArrayList<>();
        userList.add(user);

        when(userService.getAllUser()).thenReturn(userList);

        ResponseEntity<List<User>> response = userController.getAllUser();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userList, response.getBody());
        verify(userService, times(1)).getAllUser();
    }

    @Test
    void testIsUserAuthenticated() {
        when(userService.getUserByEmail("sandip@example.com")).thenReturn(user);

        ResponseEntity<User> response = userController.isUserAuthenticated("sandip@example.com");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService, times(1)).getUserByEmail("sandip@example.com");
    }

	/*
	 * @Test void testDeleteUser() {
	 * when(userService.deleteUser("u1")).thenReturn("User deleted");
	 * 
	 * ResponseEntity<String> response = userController.deleteUser("u1");
	 * 
	 * assertEquals(HttpStatus.OK, response.getStatusCode());
	 * assertEquals("User deleted", response.getBody()); verify(userService,
	 * times(1)).deleteUser("u1"); }
	 */

}
