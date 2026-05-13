package com.example.meetingroombooking.repository;

import com.example.meetingroombooking.entity.MeetingroomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingroomRepository
        extends JpaRepository<MeetingroomEntity, Long> {
}