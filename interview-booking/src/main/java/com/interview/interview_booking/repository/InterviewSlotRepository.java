package com.interview.interview_booking.repository;

import com.interview.interview_booking.entity.InterviewSlot;
import com.interview.interview_booking.enums.SlotStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface InterviewSlotRepository extends JpaRepository<InterviewSlot,Long> {

    // Get slots by interviewer
    List<InterviewSlot> findByInterviewerId(Long interviewerId);

    // Get slots by status
    List<InterviewSlot> findByStatus(SlotStatus status);

    // Conflict detection (overlapping time check)
    List<InterviewSlot> findByInterviewerIdAndStartTimeLessThanAndEndTimeGreaterThan(
            Long interviewerId,
            LocalDateTime endTime,
            LocalDateTime startTime
    );

    // Check exact slot exists
    boolean existsByInterviewerIdAndStartTimeAndEndTime(
            Long interviewerId,
            LocalDateTime startTime,
            LocalDateTime endTime
    );
}
