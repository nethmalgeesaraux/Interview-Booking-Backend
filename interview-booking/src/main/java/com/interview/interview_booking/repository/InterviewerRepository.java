package com.interview.interview_booking.repository;

import com.interview.interview_booking.entity.Interviewer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterviewerRepository extends JpaRepository<Interviewer,Long> {

    Optional<Interviewer> findByEmail(String email);

    boolean existsByEmail(String email);
}
