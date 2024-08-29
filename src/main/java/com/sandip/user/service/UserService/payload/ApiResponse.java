package com.sandip.user.service.UserService.payload;

import lombok.*;
import org.springframework.http.HttpStatus;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//Builder pattern to build this class
@Builder
public class ApiResponse {

    private  String message;
    private boolean success;
    private HttpStatus status;

}
