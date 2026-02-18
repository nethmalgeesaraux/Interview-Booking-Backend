package com.interview.interview_booking.repository;

import com.interview.interview_booking.entity.Booking;
import com.interview.interview_booking.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking,Long> {

    // Get bookings by candidate
    List<Booking> findByCandidateId(Long candidateId);

    // Get bookings by status
    List<Booking> findByStatus(BookingStatus status);

    // Check slot already booked
    Optional<Booking> findBySlotId(Long slotId);

    boolean existsBySlotId(Long slotId);
}
