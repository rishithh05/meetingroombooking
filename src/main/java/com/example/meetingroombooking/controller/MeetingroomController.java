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

    @GetMapping("/getMeetingById/{id}")
    public Object getMeetingById(
            @PathVariable String id
    ) {

        return meetingRoomService.getMeetingById(id);
    }

    @GetMapping("/getMeetingBySubject/{subject}")
    public Object getMeetingBySubject(
            @PathVariable String subject
    ) {

        return meetingRoomService.getMeetingBySubject(subject);
    }

    @PostMapping("/CreateMeetings")
    public Object addMeeting(
            @RequestBody MeetingroomEntity meetingroom
    ) {

        return meetingRoomService.addMeeting(meetingroom);
    }

    @PutMapping("/{id}")
    public Object updateMeeting(
            @PathVariable String id,
            @RequestBody MeetingroomEntity meetingroom
    ) {

        return meetingRoomService.updateMeeting(id, meetingroom);
    }

    @PatchMapping("/{id}")
    public Object patchMeeting(
            @PathVariable String id,
            @RequestBody MeetingroomEntity meetingroom
    ) {

        return meetingRoomService.patchMeeting(id, meetingroom);
    }

    @DeleteMapping("/{id}")
    public Object deleteMeeting(
            @PathVariable String id
    ) {

        return meetingRoomService.deleteMeeting(id);
    }
}