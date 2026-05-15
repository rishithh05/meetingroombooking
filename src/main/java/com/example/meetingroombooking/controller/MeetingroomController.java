package com.example.meetingroombooking.controller;

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

    private ResponseEntity<Object> successResponse(
            Object data,
            HttpStatus status
    ) {

        return ResponseEntity
                .status(status)
                .body(data);
    }

    // COMMON ERROR RESPONSE

    private ResponseEntity<Object> errorResponse(
            String message,
            HttpStatus status
    ) {

        Map<String, Object> error =
                new HashMap<>();

        error.put("statusCode", status.value());
        error.put("message", message);

        return ResponseEntity
                .status(status)
                .body(error);
    }

    // GET ALL

    @GetMapping("/getAllMeetings")
    public ResponseEntity<Object> getMeetingrooms() {

        List<Meetingroom> meetings =
                meetingRoomService.getAllMeetings();

        Map<String, Object> response =
                new HashMap<>();

        response.put("totalMeetings", meetings.size());
        response.put("meetings", meetings);

        return successResponse(
                response,
                HttpStatus.OK
        );
    }

    // GET BY ID

    @GetMapping("/getMeetingById/{id}")
    public ResponseEntity<Object> getMeetingById(
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
                HttpStatus.OK
        );
    }

    // GET BY SUBJECT

    @GetMapping("/getMeetingBySubject/{subject}")
    public ResponseEntity<Object> getMeetingBySubject(
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
                HttpStatus.OK
        );
    }

    // CREATE

    @PostMapping("/CreateMeetings")
    public ResponseEntity<Object> addMeeting(
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
                HttpStatus.CREATED
        );
    }

    // PUT

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMeeting(
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
                HttpStatus.OK
        );
    }

    // PATCH

    @PatchMapping("/{id}")
    public ResponseEntity<Object> patchMeeting(
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
                HttpStatus.OK
        );
    }

    // DELETE

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMeeting(
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
                HttpStatus.OK
        );
    }
}