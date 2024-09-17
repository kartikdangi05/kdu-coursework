package com.handson.jpa.repo;

import com.handson.jpa.entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ShiftRepo extends JpaRepository<Shift, UUID> {
    @Query("SELECT s FROM Shift s WHERE s.dateStart >= :startDate AND s.dateEnd <= :endDate ORDER BY s.name ASC")
    List<Shift> findTop3ByDateRange(LocalDate startDate, LocalDate endDate);
}
