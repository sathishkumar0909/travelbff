package com.sk.travel.repository;

import com.sk.travel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("""
        SELECT r FROM Room r
        WHERE r.roomType.id = :roomTypeId
        AND r.id NOT IN (
            SELECT ra.room.id
            FROM RoomAvailability ra
            WHERE ra.date BETWEEN :checkIn AND :checkOut
        )
    """)
    List<Room> findAvailableRooms(
            Long roomTypeId,
            LocalDate checkIn,
            LocalDate checkOut
    );
}
