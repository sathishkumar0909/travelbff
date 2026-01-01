package com.sk.travel.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String customerEmail;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private boolean offlineBooking;

    private LocalDate checkIn;
    private LocalDate checkOut;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<BookingDetail> bookingDetails;
}

