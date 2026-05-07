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
    public Object addMeeting(MeetingroomEntity meetingroom) {

        if (meetingroom.getId() == null) {
            return "Id is required";
        }

        if (meetingroom.getSubject() == null ||
                meetingroom.getSubject().isBlank()) {

            return "Subject is required";
        }

        if (meetingroom.getOrganizer() == null ||
                meetingroom.getOrganizer().isBlank()) {

            return "Organizer is required";
        }

        for (MeetingroomEntity existingMeeting :
                MeetingRoomConstants.MEETING_ROOMS) {

            if (existingMeeting.getId().equals(meetingroom.getId())) {

                return "Meeting with this id already exists";
            }
        }

        MeetingRoomConstants.MEETING_ROOMS.add(meetingroom);

        return meetingroom;
    }
    public Object patchMeeting(
            Long id,
            MeetingroomEntity updatedMeeting
    ) {

        for (MeetingroomEntity meetingroom :
                MeetingRoomConstants.MEETING_ROOMS) {

            if (meetingroom.getId().equals(id)) {

                if (updatedMeeting.getSubject() != null &&
                        !updatedMeeting.getSubject().isBlank()) {

                    meetingroom.setSubject(
                            updatedMeeting.getSubject()
                    );
                }

                if (updatedMeeting.getOrganizer() != null &&
                        !updatedMeeting.getOrganizer().isBlank()) {

                    meetingroom.setOrganizer(
                            updatedMeeting.getOrganizer()
                    );
                }

                if (updatedMeeting.getStartTime() != null &&
                        !updatedMeeting.getStartTime().isBlank()) {

                    meetingroom.setStartTime(
                            updatedMeeting.getStartTime()
                    );
                }

                if (updatedMeeting.getEndTime() != null &&
                        !updatedMeeting.getEndTime().isBlank()) {

                    meetingroom.setEndTime(
                            updatedMeeting.getEndTime()
                    );
                }

                return meetingroom;
            }
        }

        return "Meeting not found with id : " + id;
    }

    public Object updateMeeting(
            Long id,
            MeetingroomEntity updatedMeeting
    ) {

        for (MeetingroomEntity meetingroom :
                MeetingRoomConstants.MEETING_ROOMS) {

            if (meetingroom.getId().equals(id)) {

                meetingroom.setSubject(updatedMeeting.getSubject());
                meetingroom.setOrganizer(updatedMeeting.getOrganizer());
                meetingroom.setStartTime(updatedMeeting.getStartTime());
                meetingroom.setEndTime(updatedMeeting.getEndTime());

                return meetingroom;
            }
        }

        return "Meeting not found with id : " + id;
    }

    public String deleteMeeting(Long id) {

        boolean removed =
                MeetingRoomConstants.MEETING_ROOMS.removeIf(
                        meetingroom ->
                                meetingroom.getId().equals(id)
                );

        if (removed) {

            return "Meeting deleted successfully";
        }

        return "Meeting not found with id : " + id;
    }
}
