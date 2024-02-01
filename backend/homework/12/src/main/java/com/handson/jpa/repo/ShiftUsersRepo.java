package com.handson.jpa.repo;

import com.handson.jpa.entity.ShiftUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ShiftUsersRepo extends JpaRepository<ShiftUser, UUID> {

    @Query("SELECT su FROM ShiftUser su " +
            "JOIN FETCH su.shift s " +
            "JOIN FETCH su.user u " +
            "WHERE su.id = ?1 AND s.timeEnd = ?2")
    Optional<ShiftUser> findShiftUserByIdAndShiftEndTime(UUID userId, LocalTime localTime);
}
