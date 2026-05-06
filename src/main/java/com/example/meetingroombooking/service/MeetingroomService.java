package com.example.meetingroombooking.service;

import com.example.meetingroombooking.entity.MeetingroomEntity;
import java.util.List;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MeetingroomService {
    public List<MeetingroomEntity> getMeetingrooms() {
        List<MeetingroomEntity> meetingrooms = new ArrayList<>();
        meetingrooms.add(new MeetingroomEntity(1L, "Project Kickoff", "Alice", "2024-07-01T10:00", "2024-07-01T11:00"));
        meetingrooms.add(new MeetingroomEntity(2L, "Design Review", "Bob", "2024-07-01T11:30", "2024-07-01T12:30"));

        return meetingrooms;
    }
}
