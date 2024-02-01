package com.handson.jpa.repo;

import com.handson.jpa.entity.ShiftUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ShiftUsersRepo extends JpaRepository<ShiftUser, UUID> {
}
