package com.sandip.user.service.UserService.entities;

import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Builder
public class Hotel {

    private String id;
    private String name;
    private String location;
    private String about;

}
