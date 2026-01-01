package com.sk.travel.repository;

import com.sk.travel.model.Room;
import com.sk.travel.model.RoomAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface RoomAvailabilityRepository
        extends JpaRepository<RoomAvailability, Long> {

    void deleteByRoomAndDateBetween(
            Room room,
            LocalDate start,
            LocalDate end
    );
}