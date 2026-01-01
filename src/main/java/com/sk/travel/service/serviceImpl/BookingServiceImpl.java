package com.sk.travel.service.serviceImpl;

import com.sk.travel.model.*;
import com.sk.travel.repository.BookingDetailRepository;
import com.sk.travel.repository.BookingRepository;
import com.sk.travel.repository.RoomAvailabilityRepository;
import com.sk.travel.repository.RoomRepository;
import com.sk.travel.service.BookingService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;
    private final BookingDetailRepository bookingDetailRepository;
    private final RoomAvailabilityRepository availabilityRepository;

    // ==============================
    // 1️⃣ CHECK AVAILABLE ROOMS
    // ==============================
    public List<Room> getAvailableRooms(Long roomTypeId,
                                        LocalDate checkIn,
                                        LocalDate checkOut) {

        return roomRepository.findAvailableRooms(
                roomTypeId, checkIn, checkOut
        );
    }

    // ==============================
    // 2️⃣ CREATE BOOKING (ONLINE / OFFLINE)
    // ==============================
    @Transactional
    public Booking createBooking(Long roomTypeId,
                                 LocalDate checkIn,
                                 LocalDate checkOut,
                                 boolean isOffline,
                                 String customerName,
                                 String customerEmail) {

        // 1. Get available rooms
        List<Room> rooms = getAvailableRooms(roomTypeId, checkIn, checkOut);

        if (rooms.isEmpty()) {
            throw new RuntimeException("No rooms available");
        }

        Room room = rooms.get(0); // Assign first free room

        // 2. Create Booking
        Booking booking = new Booking();
        booking.setCustomerName(customerName);
        booking.setCustomerEmail(customerEmail);
        booking.setCheckIn(checkIn);
        booking.setCheckOut(checkOut);
        booking.setOfflineBooking(isOffline);
        booking.setStatus(BookingStatus.ONGOING);

        booking = bookingRepository.save(booking);

        // 3. Create Booking Detail
        BookingDetail detail = new BookingDetail();
        detail.setBooking(booking);
        detail.setRoom(room);
        detail.setPrice(room.getRoomType().getPricePerNight());

        bookingDetailRepository.save(detail);

        // 4. Block dates
        blockRoom(room, checkIn, checkOut);

        return booking;
    }

    // ==============================
    // 3️⃣ BLOCK ROOM (IMPORTANT)
    // ==============================
    public void blockRoom(Room room, LocalDate start, LocalDate end) {

        LocalDate date = start;

        while (!date.isAfter(end.minusDays(1))) {

            RoomAvailability availability = new RoomAvailability();
            availability.setRoom(room);
            availability.setDate(date);
            availability.setAvailable(false);

            availabilityRepository.save(availability);
            date = date.plusDays(1);
        }
    }

    // ==============================
    // 4️⃣ CANCEL BOOKING
    // ==============================
    @Transactional
    public void cancelBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(BookingStatus.CANCELLED);

        // Free rooms
        for (BookingDetail detail : booking.getBookingDetails()) {
            availabilityRepository.deleteByRoomAndDateBetween(
                    detail.getRoom(),
                    booking.getCheckIn(),
                    booking.getCheckOut()
            );
        }

        bookingRepository.save(booking);
    }

    // ==============================
    // 5️⃣ GET ALL BOOKINGS
    // ==============================
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}