package com.sk.travel.controller;

import com.sk.travel.model.Booking;
import com.sk.travel.model.Hotel;
import com.sk.travel.service.BookingService;
import com.sk.travel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")

public class HotelController {

    private HotelService hotelService;

    private BookingService bookingService;

    public HotelController(HotelService hotelService,BookingService bookingService){
        this.bookingService=bookingService;
        this.hotelService=hotelService;
    }


    @GetMapping("/gethotels")
    public List<Booking> getHotels() {
        return bookingService.getAllBookings();
//        return bookingService.getAvailableRooms(Long.valueOf(1), LocalDate.now(),LocalDate.now());

    }

//    @PostMapping("/addhotel")
//    public Hotel addHotel(@RequestBody Hotel hotel) {
//        return hotelService.saveHotel(hotel);
//    }
}
