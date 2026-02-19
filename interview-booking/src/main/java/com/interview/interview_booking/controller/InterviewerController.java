package com.interview.interview_booking.controller;

import com.interview.interview_booking.dto.InterviewerRequestDTO;
import com.interview.interview_booking.dto.InterviewerResponseDTO;
import com.interview.interview_booking.service.InterviewerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interviewers")
@RequiredArgsConstructor
public class InterviewerController {

    private final InterviewerService interviewerService;

    // CREATE
    @PostMapping
    public InterviewerResponseDTO create(
            @Valid @RequestBody InterviewerRequestDTO dto) {
        return interviewerService.createInterviewer(dto);
    }

    // READ ALL
    @GetMapping
    public List<InterviewerResponseDTO> getAll() {
        return interviewerService.getAll();
    }
}