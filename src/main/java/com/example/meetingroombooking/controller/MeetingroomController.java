package com.example.meetingroombooking.controller;

import org.springframework.web.bind.annotation.*;
import com.example.meetingroombooking.service.MeetingroomService;
import com.example.meetingroombooking.entity.MeetingroomEntity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api")
public class MeetingroomController {

    @Autowired
    private MeetingroomService meetingRoomService;

    @GetMapping("/getAllMeetings")
    public List<MeetingroomEntity> getMeetingrooms() {

        return meetingRoomService.getAllMeetings();
    }
    @PostMapping("/CreateMeetings")
    public Object addMeeting(
            @RequestBody MeetingroomEntity meetingroom
    ) {

        return meetingRoomService.addMeeting(meetingroom);
    }

    @PutMapping("/{id}")
    public Object updateMeeting(
            @PathVariable Long id,
            @RequestBody MeetingroomEntity meetingroom
    ) {

        return meetingRoomService.updateMeeting(id, meetingroom);
    }
    @PatchMapping("/{id}")
    public Object patchMeeting(
            @PathVariable Long id,
            @RequestBody MeetingroomEntity meetingroom
    ) {

        return meetingRoomService.patchMeeting(id, meetingroom);
    }

    @DeleteMapping("/{id}")
    public String deleteMeeting(
            @PathVariable Long id
    ) {

        return meetingRoomService.deleteMeeting(id);
    }
}
