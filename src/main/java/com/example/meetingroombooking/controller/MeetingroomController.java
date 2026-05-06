package com.example.meetingroombooking.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.meetingroombooking.service.MeetingroomService;
import com.example.meetingroombooking.entity.MeetingroomEntity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/meetingrooms")
public class MeetingroomController {

    @Autowired
    private MeetingroomService meetingroomService;

    @GetMapping("/all")
    public List<MeetingroomEntity> getMeetingrooms() {
        return meetingroomService.getMeetingrooms();
    }
}
