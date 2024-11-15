package com.sandip.user.service.UserService.services.imp;

import com.sandip.user.service.UserService.entities.Hotel;
import com.sandip.user.service.UserService.entities.Rating;
import com.sandip.user.service.UserService.entities.User;
import com.sandip.user.service.UserService.exception.ResourceNotFoundException;
import com.sandip.user.service.UserService.external.services.HotelService;
import com.sandip.user.service.UserService.repositories.UserRepository;
import com.sandip.user.service.UserService.services.UserService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    HotelService hotelService;
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User saveUser(User user) {

      String randomuserID =   UUID.randomUUID().toString();
      user.setId(randomuserID);

      return userRepository.save(user);

    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {

        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id is not found on server : " + userId));

        // Get rating by user id using rest template
        //http://localhost:8083/ratings/user/85887e60-b0bc-4f6c-a866-630e3e168905


        Rating[] ratingsOfuser =   restTemplate.getForObject("http://RATING-SERVICE-PROD/ratings/user/"+user.getId(), Rating[].class);

        List<Rating> ratings = Arrays.stream(ratingsOfuser).toList();

        List<Rating> ratingList =  ratings.stream().map(rating -> {

        //ResponseEntity<Hotel> responseEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(),Hotel.class);

      //  Hotel hotel = responseEntity.getBody();

        Hotel hotel = hotelService.getHotelById(rating.getHotelId());
        rating.setHotel(hotel);

        return rating;

     }).collect(Collectors.toList());

        System.out.println("Rating : " + ratingList);

        user.setRatingList(ratingList);

        return user;

    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getDistinctUserByEmail(String email) {
        return null;
    }

    @Override
    public String deleteUser(String userId) {
         userRepository.deleteById(userId);
         return "OK";
    }

    @Transactional
    @Override
    public User updateUser(User user) {
        String query = "UPDATE User u SET u.name = :name, u.email = :email, " +
                       "u.password = :password, u.phonenumber = :phonenumber " +
                       "WHERE u.id = :id";
        
        int rowsUpdated = entityManager.createQuery(query)
            .setParameter("name", user.getName())
            .setParameter("email", user.getEmail())
            .setParameter("password", user.getPassword())
            .setParameter("phonenumber", user.getPhonenumber())
            .setParameter("id", user.getId())
            .executeUpdate();
        
        if (rowsUpdated > 0) {
            return user;  // Return the updated user
        } else {
            throw new RuntimeException("Failed to update user with ID " + user.getId());
        }
    }
    
}
