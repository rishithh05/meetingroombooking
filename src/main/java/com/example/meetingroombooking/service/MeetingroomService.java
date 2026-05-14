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

    // GET ALL

    public List<MeetingroomEntity> getAllMeetings() {

        return repository.getAllMeetings();
    }

    // GET BY ID

    public Object getMeetingById(String id) {

        Object validation = validateMeetingId(id);

        if (validation != null) {

            return validation;
        }

        Long meetingId = Long.parseLong(id);

        Optional<MeetingroomEntity> meeting =
                repository.getMeetingByIdNative(meetingId);

        if (meeting.isPresent()) {

            return meeting.get();
        }

        return "Meeting not found with id : " + id;
    }

    // GET BY SUBJECT
public Object getMeetingBySubject(String subject) {

    if (subject == null || subject.isBlank()) {

        return "Subject is required";
    }

    List<MeetingroomEntity> meetings =
            repository.getMeetingBySubjectNative(subject);

    if (!meetings.isEmpty()) {

        return meetings;
    }

    return "Meeting not found with subject : " + subject;
}


    // CREATE

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

    // PATCH

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

    MeetingroomEntity existingMeeting =
            optionalMeeting.get();

    String subject =
            updatedMeeting.getSubject() != null
                    ? updatedMeeting.getSubject()
                    : existingMeeting.getSubject();

    String organizer =
            updatedMeeting.getOrganizer() != null
                    ? updatedMeeting.getOrganizer()
                    : existingMeeting.getOrganizer();

    String startTime =
            updatedMeeting.getStartTime() != null
                    ? updatedMeeting.getStartTime()
                    : existingMeeting.getStartTime();

    String endTime =
            updatedMeeting.getEndTime() != null
                    ? updatedMeeting.getEndTime()
                    : existingMeeting.getEndTime();

    repository.updateMeetingNative(
            meetingId,
            subject,
            organizer,
            startTime,
            endTime
    );

    return repository.findById(meetingId).get();
}

    // PUT

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

    repository.updateMeetingNative(
            meetingId,
            updatedMeeting.getSubject(),
            updatedMeeting.getOrganizer(),
            updatedMeeting.getStartTime(),
            updatedMeeting.getEndTime()
    );

    return repository.findById(meetingId).get();
}

    // DELETE

    public Object deleteMeeting(String id) {

        Object validation = validateMeetingId(id);

        if (validation != null) {

            return validation;
        }

        Long meetingId = Long.parseLong(id);

        Optional<MeetingroomEntity> optionalMeeting =
                repository.getMeetingByIdNative(meetingId);

        if (optionalMeeting.isEmpty()) {

            return "Meeting not found with id : " + id;
        }

        repository.deleteMeetingNative(meetingId);

        return "Meeting deleted successfully";
    }
}