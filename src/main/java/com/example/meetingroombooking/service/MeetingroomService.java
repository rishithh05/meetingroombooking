package com.example.meetingroombooking.service;

import com.example.meetingroombooking.constants.MeetingRoomConstants;
import com.example.meetingroombooking.entity.MeetingroomEntity;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class MeetingroomService {

    public List<MeetingroomEntity> getAllMeetings() {

        return MeetingRoomConstants.MEETING_ROOMS;
    }
}