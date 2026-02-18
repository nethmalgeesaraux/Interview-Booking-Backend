package com.interview.interview_booking.service;

import com.interview.interview_booking.dto.CandidateRequestDTO;
import com.interview.interview_booking.dto.CandidateResponseDTO;
import com.interview.interview_booking.entity.Candidate;
import com.interview.interview_booking.exception.BookingException;
import com.interview.interview_booking.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateResponseDTO createCandidate(CandidateRequestDTO dto) {

        if (candidateRepository.existsByEmail(dto.getEmail())) {
            throw new BookingException("Email already exists!");
        }

        Candidate candidate = new Candidate();
        candidate.setName(dto.getName());
        candidate.setEmail(dto.getEmail());
        candidate.setPhone(dto.getPhone());

        Candidate saved = candidateRepository.save(candidate);

        return mapToResponse(saved);
    }

    public List<CandidateResponseDTO> getAll() {
        return candidateRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private CandidateResponseDTO mapToResponse(Candidate candidate) {
        CandidateResponseDTO dto = new CandidateResponseDTO();
        dto.setId(candidate.getId());
        dto.setName(candidate.getName());
        dto.setEmail(candidate.getEmail());
        dto.setPhone(candidate.getPhone());
        dto.setCreatedAt(candidate.getCreatedAt());
        return dto;
    }
}
