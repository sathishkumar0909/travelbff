package com.sk.travel.repository;

import com.sk.travel.model.BookingDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingDetailRepository extends JpaRepository<BookingDetail, Long> {
}

