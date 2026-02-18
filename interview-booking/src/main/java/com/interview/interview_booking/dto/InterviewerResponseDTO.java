package com.interview.interview_booking.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InterviewerResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String department;
    private LocalDateTime createdAt;

}
