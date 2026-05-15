package com.example.meetingroombooking.service;

import com.example.meetingroombooking.mapper.MeetingroomMapper;
import com.example.meetingroombooking.model.Meetingroom;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeetingroomService {

    @Autowired
    private MeetingroomMapper mapper;

    // COMMON ID VALIDATION

    private Object validateMeetingId(String id) {

        if (id == null || id.isBlank()) {

            return "Meeting id is required";
        }

        try {

            Long.parseLong(id);

        } catch (NumberFormatException e) {

            return "Invalid meeting id. Id should be a number";
        }

        return null;
    }

    // GET ALL

    public List<Meetingroom> getAllMeetings() {

        return mapper.getAllMeetings();
    }

    // GET BY ID

    public Object getMeetingById(String id) {

        Object validation = validateMeetingId(id);

        if (validation != null) {

            return validation;
        }

        Long meetingId = Long.parseLong(id);

        Meetingroom meeting =
                mapper.getMeetingById(meetingId);

        if (meeting == null) {

            return "Meeting not found with id : " + id;
        }

        return meeting;
    }

    // GET BY SUBJECT

    public Object getMeetingBySubject(String subject) {

        if (subject == null || subject.isBlank()) {

            return "Subject is required";
        }

        List<Meetingroom> meetings =
                mapper.getMeetingBySubject(subject);

        if (meetings.isEmpty()) {

            return "Meeting not found with subject : " + subject;
        }

        return meetings;
    }

    // CREATE

    public Object addMeeting(Meetingroom meetingroom) {

        if (meetingroom.getSubject() == null ||
                meetingroom.getSubject().isBlank()) {

            return "Subject is required";
        }

        if (meetingroom.getOrganizer() == null ||
                meetingroom.getOrganizer().isBlank()) {

            return "Organizer is required";
        }

        mapper.createMeeting(meetingroom);

        return meetingroom;
    }

    // PATCH

    public Object patchMeeting(
            String id,
            Meetingroom updatedMeeting) {

        Object validation = validateMeetingId(id);

        if (validation != null) {

            return validation;
        }

        Long meetingId = Long.parseLong(id);

        Meetingroom existingMeeting =
                mapper.getMeetingById(meetingId);

        if (existingMeeting == null) {

            return "Meeting not found with id : " + id;
        }

        String subject =
                updatedMeeting.getSubject() != null
                        ? updatedMeeting.getSubject()
                        : existingMeeting.getSubject();

        String organizer =
                updatedMeeting.getOrganizer() != null
                        ? updatedMeeting.getOrganizer()
                        : existingMeeting.getOrganizer();

        LocalDateTime startTime =
                updatedMeeting.getStartTime() != null
                        ? updatedMeeting.getStartTime()
                        : existingMeeting.getStartTime();

        LocalDateTime endTime =
                updatedMeeting.getEndTime() != null
                        ? updatedMeeting.getEndTime()
                        : existingMeeting.getEndTime();

        Meetingroom meetingroom =
                new Meetingroom(
                        meetingId,
                        subject,
                        organizer,
                        startTime,
                        endTime
                );

        mapper.updateMeeting(meetingroom);

        return mapper.getMeetingById(meetingId);
    }

    // PUT

    public Object updateMeeting(
            String id,
            Meetingroom updatedMeeting) {

        Object validation = validateMeetingId(id);

        if (validation != null) {

            return validation;
        }

        Long meetingId = Long.parseLong(id);

        Meetingroom existingMeeting =
                mapper.getMeetingById(meetingId);

        if (existingMeeting == null) {

            return "Meeting not found with id : " + id;
        }

        updatedMeeting.setId(meetingId);

        mapper.updateMeeting(updatedMeeting);

        return mapper.getMeetingById(meetingId);
    }

    // DELETE

    public Object deleteMeeting(String id) {

        Object validation = validateMeetingId(id);

        if (validation != null) {

            return validation;
        }

        Long meetingId = Long.parseLong(id);

        Meetingroom meeting =
                mapper.getMeetingById(meetingId);

        if (meeting == null) {

            return "Meeting not found with id : " + id;
        }

        mapper.deleteMeeting(meetingId);

        return "Meeting deleted successfully";
    }
}