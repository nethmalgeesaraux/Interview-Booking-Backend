package com.interview.interview_booking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "interviewers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Interviewer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String department;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "interviewer", cascade = CascadeType.ALL)
    private List<InterviewSlot> slots;
}
