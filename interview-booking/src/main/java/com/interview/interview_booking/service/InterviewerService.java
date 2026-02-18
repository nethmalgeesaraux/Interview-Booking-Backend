package com.interview.interview_booking.service;

import com.interview.interview_booking.dto.InterviewerRequestDTO;
import com.interview.interview_booking.dto.InterviewerResponseDTO;
import com.interview.interview_booking.entity.Interviewer;
import com.interview.interview_booking.exception.BookingException;
import com.interview.interview_booking.repository.InterviewerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InterviewerService {

    private final InterviewerRepository interviewerRepository;

    public InterviewerResponseDTO createInterviewer(InterviewerRequestDTO dto) {

        if (interviewerRepository.existsByEmail(dto.getEmail())) {
            throw new BookingException("Email already exists!");
        }

        Interviewer interviewer = new Interviewer();
        interviewer.setName(dto.getName());
        interviewer.setEmail(dto.getEmail());
        interviewer.setDepartment(dto.getDepartment());

        Interviewer saved = interviewerRepository.save(interviewer);

        return mapToResponse(saved);
    }

    public List<InterviewerResponseDTO> getAll() {
        return interviewerRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private InterviewerResponseDTO mapToResponse(Interviewer interviewer) {
        InterviewerResponseDTO dto = new InterviewerResponseDTO();
        dto.setId(interviewer.getId());
        dto.setName(interviewer.getName());
        dto.setEmail(interviewer.getEmail());
        dto.setDepartment(interviewer.getDepartment());
        dto.setCreatedAt(interviewer.getCreatedAt());
        return dto;
    }
}
