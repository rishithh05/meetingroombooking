package com.example.meetingroombooking.constants;

import com.example.meetingroombooking.entity.MeetingroomEntity;

import java.util.List;

public class MeetingRoomConstants {

    public static final List<MeetingroomEntity> MEETING_ROOMS = List.of(

            new MeetingroomEntity(
                    1L,
                    "Project Kickoff",
                    "Alice",
                    "2024-07-01T10:00",
                    "2024-07-01T11:00"
            ),

            new MeetingroomEntity(
                    2L,
                    "Design Review",
                    "Bob",
                    "2024-07-01T11:30",
                    "2024-07-01T12:30"
            )
    );

    private MeetingRoomConstants() {
    }
}

