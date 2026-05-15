package com.example.meetingroombooking.mapper;

import org.apache.ibatis.annotations.*;

import com.example.meetingroombooking.model.Meetingroom;

import java.util.List;

@Mapper
public interface MeetingroomMapper {

    // GET ALL

    @Select("""
            SELECT *
            FROM meeting_rooms
            """)
    List<Meetingroom> getAllMeetings();

    // GET BY ID

    @Select("""
            SELECT *
            FROM meeting_rooms
            WHERE id = #{id}
            """)
    Meetingroom getMeetingById(Long id);

    // GET BY SUBJECT

    @Select("""
            SELECT *
            FROM meeting_rooms
            WHERE LOWER(REPLACE(subject,' ','')) =
                  LOWER(REPLACE(#{subject},' ',''))
            """)
    List<Meetingroom> getMeetingBySubject(
            String subject
    );

    // CREATE

    @Insert("""
            INSERT INTO meeting_rooms
            (
                subject,
                organizer,
                start_time,
                end_time
            )
            VALUES
            (
                #{subject},
                #{organizer},
                #{startTime},
                #{endTime}
            )
            """)
    @Options(
            useGeneratedKeys = true,
            keyProperty = "id"
    )
    int createMeeting(
            Meetingroom meetingroom
    );

    // UPDATE

    @Update("""
            UPDATE meeting_rooms
            SET subject = #{subject},
                organizer = #{organizer},
                start_time = #{startTime},
                end_time = #{endTime}
            WHERE id = #{id}
            """)
    int updateMeeting(
            Meetingroom meetingroom
    );

    // DELETE

    @Delete("""
            DELETE FROM meeting_rooms
            WHERE id = #{id}
            """)
    int deleteMeeting(Long id);
}