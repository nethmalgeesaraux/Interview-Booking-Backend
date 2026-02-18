package com.interview.interview_booking.service;

import com.interview.interview_booking.dto.BookingRequestDTO;
import com.interview.interview_booking.dto.BookingResponseDTO;
import com.interview.interview_booking.entity.Booking;
import com.interview.interview_booking.entity.Candidate;
import com.interview.interview_booking.entity.InterviewSlot;
import com.interview.interview_booking.enums.BookingStatus;
import com.interview.interview_booking.enums.SlotStatus;
import com.interview.interview_booking.exception.BookingException;
import com.interview.interview_booking.repository.BookingRepository;
import com.interview.interview_booking.repository.CandidateRepository;
import com.interview.interview_booking.repository.InterviewSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final CandidateRepository candidateRepository;
    private final InterviewSlotRepository slotRepository;

    public BookingResponseDTO createBooking(BookingRequestDTO dto) {

        Candidate candidate = candidateRepository.findById(dto.getCandidateId())
                .orElseThrow(() -> new BookingException("Candidate not found"));

        InterviewSlot slot = slotRepository.findById(dto.getSlotId())
                .orElseThrow(() -> new BookingException("Slot not found"));

        if (slot.getStatus() == SlotStatus.BOOKED) {
            throw new BookingException("Slot already booked!");
        }

        if (slot.getStartTime().isBefore(LocalDateTime.now())) {
            throw new BookingException("Cannot book past time slot!");
        }

        Booking booking = new Booking();
        booking.setCandidate(candidate);
        booking.setSlot(slot);
        booking.setBookedAt(LocalDateTime.now());
        booking.setStatus(BookingStatus.CONFIRMED);

        slot.setStatus(SlotStatus.BOOKED);
        slotRepository.save(slot);

        Booking saved = bookingRepository.save(booking);

        // Email Simulation
        System.out.println("Email Sent to " + candidate.getEmail()
                + " for interview at " + slot.getStartTime());

        return mapToResponse(saved);
    }


    public BookingResponseDTO cancelBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingException("Booking not found"));

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new BookingException("Booking already cancelled!");
        }

        if (booking.getSlot().getStartTime().isBefore(LocalDateTime.now())) {
            throw new BookingException("Cannot cancel past interview!");
        }

        booking.setStatus(BookingStatus.CANCELLED);

        InterviewSlot slot = booking.getSlot();
        slot.setStatus(SlotStatus.AVAILABLE);
        slotRepository.save(slot);

        Booking saved = bookingRepository.save(booking);

        System.out.println("Cancellation Email Sent to "
                + booking.getCandidate().getEmail()
                + " for interview at "
                + slot.getStartTime());

        return mapToResponse(saved);
    }


    private BookingResponseDTO mapToResponse(Booking booking) {
        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setId(booking.getId());
        dto.setCandidateId(booking.getCandidate().getId());
        dto.setCandidateName(booking.getCandidate().getName());
        dto.setSlotId(booking.getSlot().getId());
        dto.setStartTime(booking.getSlot().getStartTime());
        dto.setEndTime(booking.getSlot().getEndTime());
        dto.setInterviewerName(booking.getSlot().getInterviewer().getName());
        dto.setStatus(booking.getStatus());
        dto.setBookedAt(booking.getBookedAt());
        return dto;
    }
}
