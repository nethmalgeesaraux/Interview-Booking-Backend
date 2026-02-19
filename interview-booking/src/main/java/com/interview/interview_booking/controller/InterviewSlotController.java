package com.interview.interview_booking.controller;

import com.interview.interview_booking.dto.SlotRequestDTO;
import com.interview.interview_booking.dto.SlotResponseDTO;
import com.interview.interview_booking.service.InterviewSlotService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slots")
@RequiredArgsConstructor
public class InterviewSlotController {

    private final InterviewSlotService slotService;

    // CREATE SLOT
    @PostMapping
    public SlotResponseDTO createSlot(
            @Valid @RequestBody SlotRequestDTO dto) {
        return slotService.createSlot(dto);
    }

    // GET ALL SLOTS
    @GetMapping
    public List<SlotResponseDTO> getAll() {
        return slotService.getAllSlots();
    }
}