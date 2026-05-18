package com.example.meetingroombooking.controller;

import com.example.meetingroombooking.model.GenericResponse;
import org.springframework.web.bind.annotation.*;

import com.example.meetingroombooking.model.Meetingroom;
import com.example.meetingroombooking.service.MeetingroomService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class MeetingroomController {

    @Autowired
    private MeetingroomService meetingRoomService;

    // COMMON SUCCESS RESPONSE

    private <T> ResponseEntity<GenericResponse<T>>
    successResponse(
            T data,
            String message,
            HttpStatus status
    ) {

        GenericResponse<T> response =
                new GenericResponse<>(
                        status.value(),
                        message,
                        data
                );

        return ResponseEntity
                .status(status)
                .body(response);
    }

    // COMMON ERROR RESPONSE

    private ResponseEntity<GenericResponse<Object>>
    errorResponse(
            String message,
            HttpStatus status
    ) {

        GenericResponse<Object> response =
                new GenericResponse<>(
                        status.value(),
                        message,
                        null
                );

        return ResponseEntity
                .status(status)
                .body(response);
    }

    // GET ALL

    @GetMapping("/getAllMeetings")
    public ResponseEntity<GenericResponse<Map<String, Object>>>
    getMeetingrooms() {

        List<Meetingroom> meetings =
                meetingRoomService.getAllMeetings();

        Map<String, Object> response =
                new HashMap<>();

        response.put("totalMeetings", meetings.size());
        response.put("meetings", meetings);

        return successResponse(
                response,
                "Meetings fetched successfully",
                HttpStatus.OK
        );
    }

    // GET BY ID

    @GetMapping("/getMeetingById/{id}")
    public ResponseEntity<GenericResponse<Object>>
    getMeetingById(
            @PathVariable String id
    ) {

        Object response =
                meetingRoomService.getMeetingById(id);

        if (response instanceof String) {

            return errorResponse(
                    response.toString(),
                    HttpStatus.NOT_FOUND
            );
        }

        return successResponse(
                response,
                "Meeting fetched successfully",
                HttpStatus.OK
        );
    }

    // GET BY SUBJECT

    @GetMapping("/getMeetingBySubject/{subject}")
    public ResponseEntity<GenericResponse<Object>>
    getMeetingBySubject(
            @PathVariable String subject
    ) {

        Object response =
                meetingRoomService.getMeetingBySubject(subject);

        if (response instanceof String) {

            return errorResponse(
                    response.toString(),
                    HttpStatus.NOT_FOUND
            );
        }

        return successResponse(
                response,
                "Meeting fetched successfully",
                HttpStatus.OK
        );
    }

    // CREATE

    @PostMapping("/CreateMeetings")
    public ResponseEntity<GenericResponse<Object>>
    addMeeting(
            @RequestBody Meetingroom meetingroom
    ) {

        Object response =
                meetingRoomService.addMeeting(meetingroom);

        if (response instanceof String) {

            return errorResponse(
                    response.toString(),
                    HttpStatus.BAD_REQUEST
            );
        }

        return successResponse(
                response,
                "Meeting created successfully",
                HttpStatus.CREATED
        );
    }

    // PUT

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<Object>>
    updateMeeting(
            @PathVariable String id,
            @RequestBody Meetingroom meetingroom
    ) {

        Object response =
                meetingRoomService.updateMeeting(id, meetingroom);

        if (response instanceof String) {

            return errorResponse(
                    response.toString(),
                    HttpStatus.NOT_FOUND
            );
        }

        return successResponse(
                response,
                "Meeting updated successfully",
                HttpStatus.OK
        );
    }

    // PATCH

    @PatchMapping("/{id}")
    public ResponseEntity<GenericResponse<Object>>
    patchMeeting(
            @PathVariable String id,
            @RequestBody Meetingroom meetingroom
    ) {

        Object response =
                meetingRoomService.patchMeeting(id, meetingroom);

        if (response instanceof String) {

            return errorResponse(
                    response.toString(),
                    HttpStatus.NOT_FOUND
            );
        }

        return successResponse(
                response,
                "Meeting patched successfully",
                HttpStatus.OK
        );
    }

    // DELETE

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Object>>
    deleteMeeting(
            @PathVariable String id
    ) {

        Object response =
                meetingRoomService.deleteMeeting(id);

        if (response.toString().contains("not found")) {

            return errorResponse(
                    response.toString(),
                    HttpStatus.NOT_FOUND
            );
        }

        return successResponse(
                response,
                "Meeting deleted successfully",
                HttpStatus.OK
        );
    }
}