package com.interview.interview_booking.service;

import com.interview.interview_booking.dto.SlotRequestDTO;
import com.interview.interview_booking.dto.SlotResponseDTO;
import com.interview.interview_booking.entity.InterviewSlot;
import com.interview.interview_booking.entity.Interviewer;
import com.interview.interview_booking.enums.SlotStatus;
import com.interview.interview_booking.exception.BookingException;
import com.interview.interview_booking.repository.InterviewSlotRepository;
import com.interview.interview_booking.repository.InterviewerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InterviewSlotService {


    private final InterviewSlotRepository slotRepository;
    private final InterviewerRepository interviewerRepository;

    public SlotResponseDTO createSlot(SlotRequestDTO dto) {

        if (dto.getStartTime().isAfter(dto.getEndTime())) {
            throw new BookingException("Start time must be before end time!");
        }

        if (dto.getStartTime().isBefore(LocalDateTime.now())) {
            throw new BookingException("Cannot create past slot!");
        }

        Interviewer interviewer = interviewerRepository.findById(dto.getInterviewerId())
                .orElseThrow(() -> new BookingException("Interviewer not found"));

        List<InterviewSlot> conflicts =
                slotRepository.findByInterviewerIdAndStartTimeLessThanAndEndTimeGreaterThan(
                        dto.getInterviewerId(),
                        dto.getEndTime(),
                        dto.getStartTime()
                );

        if (!conflicts.isEmpty()) {
            throw new BookingException("Interviewer already has a slot in this time range!");
        }

        InterviewSlot slot = new InterviewSlot();
        slot.setStartTime(dto.getStartTime());
        slot.setEndTime(dto.getEndTime());
        slot.setStatus(SlotStatus.AVAILABLE);
        slot.setInterviewer(interviewer);

        InterviewSlot saved = slotRepository.save(slot);

        return mapToResponse(saved);
    }

    public List<SlotResponseDTO> getAllSlots() {
        return slotRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private SlotResponseDTO mapToResponse(InterviewSlot slot) {
        SlotResponseDTO dto = new SlotResponseDTO();
        dto.setId(slot.getId());
        dto.setStartTime(slot.getStartTime());
        dto.setEndTime(slot.getEndTime());
        dto.setStatus(slot.getStatus());
        dto.setInterviewerId(slot.getInterviewer().getId());
        dto.setInterviewerName(slot.getInterviewer().getName());
        return dto;
    }
}
