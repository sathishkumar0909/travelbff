package com.sk.travel.service;

import com.sk.travel.model.Booking;
import com.sk.travel.model.Room;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    public List<Room> getAvailableRooms(Long roomTypeId, LocalDate checkIn, LocalDate checkOut);
    public Booking createBooking(Long roomTypeId,LocalDate checkIn,LocalDate checkOut,boolean isOffline,String customerName,String customerEmail);
    public void blockRoom(Room room, LocalDate start, LocalDate end);
    public void cancelBooking(Long bookingId);
    public List<Booking> getAllBookings();
}
