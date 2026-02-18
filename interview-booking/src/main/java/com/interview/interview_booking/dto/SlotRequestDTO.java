package com.interview.interview_booking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SlotRequestDTO {

    @NotNull(message = "Start time required")
    private LocalDateTime startTime;

    @NotNull(message = "End time required")
    private LocalDateTime endTime;

    @NotNull(message = "Interviewer ID required")
    private Long interviewerId;
}
