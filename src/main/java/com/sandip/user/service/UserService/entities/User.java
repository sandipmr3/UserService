package com.sandip.user.service.UserService.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "micro_user")
public class User {

    @Id
    @Column(name="ID")
    @NotBlank(message = "userId should not be null")
    private String userId;
    @Column(name="NAME")
    @NotBlank(message = "name should not be null")
    private  String name;
    @Column(name="EMAIL")
    @NotBlank(message = "email should not be null")
    private  String email;
    @Column(name="PASSWORD")
    @NotBlank(message = "password should not be null")
    private String password;
    @Column(name="PHONENUMBER")
    @NotBlank(message = "phonenumber should not be null")
    private String phonenumber;

    @Transient
    private List<Rating> ratingList = new ArrayList<>();

}
