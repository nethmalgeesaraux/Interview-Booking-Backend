package com.interview.interview_booking.repository;

import com.interview.interview_booking.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate,Long> {

    // Check email already exists
    Optional<Candidate> findByEmail(String email);

    boolean existsByEmail(String email);
}
