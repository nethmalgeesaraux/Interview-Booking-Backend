package com.interview.interview_booking.controller;

import com.interview.interview_booking.dto.CandidateRequestDTO;
import com.interview.interview_booking.dto.CandidateResponseDTO;
import com.interview.interview_booking.service.CandidateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    // CREATE
    @PostMapping
    public CandidateResponseDTO create(
            @Valid @RequestBody CandidateRequestDTO dto) {
        return candidateService.createCandidate(dto);
    }

    // READ ALL
    @GetMapping
    public List<CandidateResponseDTO> getAll() {
        return candidateService.getAll();
    }

}