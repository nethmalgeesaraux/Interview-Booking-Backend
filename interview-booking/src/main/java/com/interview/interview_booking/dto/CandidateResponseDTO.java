package com.interview.interview_booking.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CandidateResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
}
