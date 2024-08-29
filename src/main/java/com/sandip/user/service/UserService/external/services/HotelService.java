package com.sandip.user.service.UserService.external.services;

import com.sandip.user.service.UserService.entities.Hotel;
import jakarta.ws.rs.Path;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="HOTEL-SERVICE")
public interface HotelService {


    @GetMapping("/hotel/{hotelId}")
    Hotel getHotelById(@PathVariable String hotelId);

}
