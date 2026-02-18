package com.interview.interview_booking.dto;

import com.interview.interview_booking.enums.BookingStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingResponseDTO {

    private Long id;

    private Long candidateId;
    private String candidateName;

    private Long slotId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String interviewerName;

    private BookingStatus status;

    private LocalDateTime bookedAt;
}
