package com.sk.travel.repository;

import com.sk.travel.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
//    public List<Hotel> getAllHotels();
}
