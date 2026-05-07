package com.example.meetingroombooking.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.meetingroombooking.service.MeetingroomService;
import com.example.meetingroombooking.entity.MeetingroomEntity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class MeetingroomController {

    @Autowired
    private MeetingroomService meetingroomService;

    @GetMapping("/getAllMeetings")
    public List<MeetingroomEntity> getMeetingrooms() {
        return meetingroomService.getAllMeetings();
    }
}
