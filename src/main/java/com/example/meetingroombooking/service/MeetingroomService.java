package com.example.meetingroombooking.service;

import com.example.meetingroombooking.entity.MeetingroomEntity;
import com.example.meetingroombooking.repository.MeetingroomRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeetingroomService {

    @Autowired
    private MeetingroomRepository repository;

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

    public List<MeetingroomEntity> getAllMeetings() {

        return repository.findAll();
    }

    public Object getMeetingById(String id) {

        Object validation = validateMeetingId(id);

        if (validation != null) {

            return validation;
        }

        Long meetingId = Long.parseLong(id);

        Optional<MeetingroomEntity> meeting =
                repository.findById(meetingId);

        if (meeting.isPresent()) {

            return meeting.get();
        }

        return "Meeting not found with id : " + id;
    }

    public Object getMeetingBySubject(String subject) {

        if (subject == null || subject.isBlank()) {

            return "Subject is required";
        }

        String formattedSubject =
                subject.toLowerCase().replaceAll("\\s+", "");

        List<MeetingroomEntity> meetings =
                repository.findAll();

        for (MeetingroomEntity meetingroom : meetings) {

            String storedSubject =
                    meetingroom.getSubject()
                            .toLowerCase()
                            .replaceAll("\\s+", "");

            if (storedSubject.equals(formattedSubject)) {

                return meetingroom;
            }
        }

        return "Meeting not found with subject : " + subject;
    }

    public Object addMeeting(MeetingroomEntity meetingroom) {

        if (meetingroom.getSubject() == null ||
                meetingroom.getSubject().isBlank()) {

            return "Subject is required";
        }

        if (meetingroom.getOrganizer() == null ||
                meetingroom.getOrganizer().isBlank()) {

            return "Organizer is required";
        }

        return repository.save(meetingroom);
    }

    public Object patchMeeting(
            String id,
            MeetingroomEntity updatedMeeting) {

        Object validation = validateMeetingId(id);

        if (validation != null) {

            return validation;
        }

        Long meetingId = Long.parseLong(id);

        Optional<MeetingroomEntity> optionalMeeting =
                repository.findById(meetingId);

        if (optionalMeeting.isEmpty()) {

            return "Meeting not found with id : " + id;
        }

        MeetingroomEntity meetingroom =
                optionalMeeting.get();

        if (updatedMeeting.getSubject() != null &&
                !updatedMeeting.getSubject().isBlank()) {

            meetingroom.setSubject(
                    updatedMeeting.getSubject());
        }

        if (updatedMeeting.getOrganizer() != null &&
                !updatedMeeting.getOrganizer().isBlank()) {

            meetingroom.setOrganizer(
                    updatedMeeting.getOrganizer());
        }

        if (updatedMeeting.getStartTime() != null &&
                !updatedMeeting.getStartTime().isBlank()) {

            meetingroom.setStartTime(
                    updatedMeeting.getStartTime());
        }

        if (updatedMeeting.getEndTime() != null &&
                !updatedMeeting.getEndTime().isBlank()) {

            meetingroom.setEndTime(
                    updatedMeeting.getEndTime());
        }

        return repository.save(meetingroom);
    }

    public Object updateMeeting(
            String id,
            MeetingroomEntity updatedMeeting) {

        Object validation = validateMeetingId(id);

        if (validation != null) {

            return validation;
        }

        Long meetingId = Long.parseLong(id);

        Optional<MeetingroomEntity> optionalMeeting =
                repository.findById(meetingId);

        if (optionalMeeting.isEmpty()) {

            return "Meeting not found with id : " + id;
        }

        MeetingroomEntity meetingroom =
                optionalMeeting.get();

        meetingroom.setSubject(updatedMeeting.getSubject());
        meetingroom.setOrganizer(updatedMeeting.getOrganizer());
        meetingroom.setStartTime(updatedMeeting.getStartTime());
        meetingroom.setEndTime(updatedMeeting.getEndTime());

        return repository.save(meetingroom);
    }

    public Object deleteMeeting(String id) {

        Object validation = validateMeetingId(id);

        if (validation != null) {

            return validation;
        }

        Long meetingId = Long.parseLong(id);

        Optional<MeetingroomEntity> optionalMeeting =
                repository.findById(meetingId);

        if (optionalMeeting.isEmpty()) {

            return "Meeting not found with id : " + id;
        }

        repository.deleteById(meetingId);

        return "Meeting deleted successfully";
    }
}