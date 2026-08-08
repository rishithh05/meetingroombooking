package com.example.meetingroombooking.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meetingroom {

    private Long id;

    private String subject;

    private String organizer;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
    private long duration;
}