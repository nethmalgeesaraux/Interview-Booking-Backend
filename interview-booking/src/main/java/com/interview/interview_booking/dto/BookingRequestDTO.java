package com.interview.interview_booking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookingRequestDTO {

    @NotNull(message = "Candidate ID required")
    private Long candidateId;

    @NotNull(message = "Slot ID required")
    private Long slotId;
}
