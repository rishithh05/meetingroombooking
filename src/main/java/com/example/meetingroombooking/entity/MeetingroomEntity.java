package com.example.meetingroombooking.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MeetingroomEntity {
    private Long id;
    private String subject;
    private String organizer;
    private String startTime;
    private String endTime;
}
