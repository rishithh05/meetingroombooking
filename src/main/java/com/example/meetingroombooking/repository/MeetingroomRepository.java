package com.example.meetingroombooking.repository;

import com.example.meetingroombooking.entity.MeetingroomEntity;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MeetingroomRepository
        extends JpaRepository<MeetingroomEntity, Long> {

    // GET ALL

    @Query(
            value = "SELECT * FROM meeting_rooms",
            nativeQuery = true
    )
    List<MeetingroomEntity> getAllMeetings();

    // GET BY ID

    @Query(
            value = """
                    SELECT * 
                    FROM meeting_rooms
                    WHERE id = :id
                    """,
            nativeQuery = true
    )
    Optional<MeetingroomEntity> getMeetingByIdNative(
            @Param("id") Long id
    );

    // GET BY SUBJECT

   @Query(
        value = """
                SELECT *
                FROM meeting_rooms
                WHERE LOWER(REPLACE(subject,' ','')) =
                LOWER(REPLACE(:subject,' ',''))
                """,
        nativeQuery = true
)
List<MeetingroomEntity> getMeetingBySubjectNative(
        @Param("subject") String subject
);
    // DELETE

    @Transactional
    @Modifying
    @Query(
            value = """
                    DELETE FROM meeting_rooms
                    WHERE id = :id
                    """,
            nativeQuery = true
    )
    void deleteMeetingNative(
            @Param("id") Long id
    );

    // PUT / PATCH UPDATE

    @Transactional
@Modifying(
        clearAutomatically = true,
        flushAutomatically = true
)
@Query(
        value = """
                UPDATE meeting_rooms
                SET subject = :subject,
                    organizer = :organizer,
                    start_time = :startTime,
                    end_time = :endTime
                WHERE id = :id
                """,
        nativeQuery = true
)
int updateMeetingNative(
        @Param("id") Long id,
        @Param("subject") String subject,
        @Param("organizer") String organizer,
        @Param("startTime") String startTime,
        @Param("endTime") String endTime
);
}