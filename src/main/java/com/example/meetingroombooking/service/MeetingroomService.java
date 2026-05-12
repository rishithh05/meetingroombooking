package com.example.meetingroombooking.service;

import com.example.meetingroombooking.constants.MeetingRoomConstants;
import com.example.meetingroombooking.entity.MeetingroomEntity;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MeetingroomService {

    private MeetingRoomConstants meetingRoomConstants;

    private Long id = 3L;

    public MeetingroomService() {

        this.meetingRoomConstants = new MeetingRoomConstants();
    }

    public List<MeetingroomEntity> getAllMeetings() {

        return meetingRoomConstants.MEETING_ROOMS;
    }

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

    public Object getMeetingById(String id) {

        Object validation = validateMeetingId(id);

        if (validation != null) {

            return validation;
        }

        Long meetingId = Long.parseLong(id);

        for (MeetingroomEntity meetingroom :
                meetingRoomConstants.MEETING_ROOMS) {

            if (meetingroom.getId().equals(meetingId)) {

                return meetingroom;
            }
        }

        return "Meeting not found with id : " + id;
    }

   public Object getMeetingBySubject(String subject) {

    if (subject == null || subject.isBlank()) {

        return "Subject is required";
    }

    String formattedSubject =
            subject.toLowerCase().replaceAll("\\s+", "");

    for (MeetingroomEntity meetingroom :
            meetingRoomConstants.MEETING_ROOMS) {

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

        meetingroom.setId(id++);

        meetingRoomConstants.MEETING_ROOMS.add(meetingroom);

        return meetingroom;
    }

    public Object patchMeeting(
            String id,
            MeetingroomEntity updatedMeeting) {

        Object validation = validateMeetingId(id);

        if (validation != null) {

            return validation;
        }

        Long meetingId = Long.parseLong(id);

        for (MeetingroomEntity meetingroom :
                meetingRoomConstants.MEETING_ROOMS) {

            if (meetingroom.getId().equals(meetingId)) {

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

                return meetingroom;
            }
        }

        return "Meeting not found with id : " + id;
    }

    public Object updateMeeting(
            String id,
            MeetingroomEntity updatedMeeting) {

        Object validation = validateMeetingId(id);

        if (validation != null) {

            return validation;
        }

        Long meetingId = Long.parseLong(id);

        for (MeetingroomEntity meetingroom :
                meetingRoomConstants.MEETING_ROOMS) {

            if (meetingroom.getId().equals(meetingId)) {

                meetingroom.setSubject(updatedMeeting.getSubject());
                meetingroom.setOrganizer(updatedMeeting.getOrganizer());
                meetingroom.setStartTime(updatedMeeting.getStartTime());
                meetingroom.setEndTime(updatedMeeting.getEndTime());

                return meetingroom;
            }
        }

        return "Meeting not found with id : " + id;
    }

    public Object deleteMeeting(String id) {

        Object validation = validateMeetingId(id);

        if (validation != null) {

            return validation;
        }

        Long meetingId = Long.parseLong(id);

        boolean removed =
                meetingRoomConstants.MEETING_ROOMS.removeIf(
                        meetingroom ->
                                meetingroom.getId().equals(meetingId));

        if (removed) {

            return "Meeting deleted successfully";
        }

        return "Meeting not found with id : " + id;
    }
}