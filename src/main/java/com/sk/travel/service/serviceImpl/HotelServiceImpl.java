package com.sk.travel.service.serviceImpl;

import com.sk.travel.model.Hotel;
import com.sk.travel.repository.HotelRepository;
import com.sk.travel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

//    @Autowired
//    private HotelRepository hotelRepository;
//    @Override
//    public List<Hotel> getAllHotels() {
//        return hotelRepository.findAll();
//    }
//
//    @Override
//    public Hotel saveHotel(Hotel hotel)
//    {
//        if (hotel.getRooms() != null && !hotel.getRooms().isEmpty()) {
//            hotel.getRooms().forEach(room -> room.setHotel(hotel));
//        }
//        System.out.println("Location received: " + hotel.getLocation());
//
//        return hotelRepository.save(hotel);
//    }
}
