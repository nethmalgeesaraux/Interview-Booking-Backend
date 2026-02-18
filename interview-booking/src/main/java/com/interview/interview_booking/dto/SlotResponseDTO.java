package com.interview.interview_booking.dto;

import com.interview.interview_booking.enums.SlotStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SlotResponseDTO {

    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private SlotStatus status;

    private Long interviewerId;
    private String interviewerName;
}
