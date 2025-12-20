package com.sk.travel.controller;

import com.sk.travel.model.Hotel;
import com.sk.travel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("/gethotels")
    public List<Hotel> getHotels() {
        return hotelService.getAllHotels();
    }

    @PostMapping("/addhotel")
    public Hotel addHotel(@RequestBody Hotel hotel) {
        return hotelService.saveHotel(hotel);
    }
}
