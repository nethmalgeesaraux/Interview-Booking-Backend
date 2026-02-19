package com.interview.interview_booking.controller;

import com.interview.interview_booking.dto.BookingRequestDTO;
import com.interview.interview_booking.dto.BookingResponseDTO;
import com.interview.interview_booking.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    // CREATE BOOKING
    @PostMapping
    public BookingResponseDTO createBooking(
            @Valid @RequestBody BookingRequestDTO dto) {
        return bookingService.createBooking(dto);
    }

    // CANCEL BOOKING
    @PutMapping("/cancel/{id}")
    public BookingResponseDTO cancelBooking(@PathVariable Long id) {
        return bookingService.cancelBooking(id);
    }
}