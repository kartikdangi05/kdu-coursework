package com.handson.jpa.repo;

import com.handson.jpa.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {

    @Query("UPDATE User SET username = ?1, loggedIn = ?2, timeZone = ?3 WHERE id = ?4")
    Optional<User> updateUser(String username, int loggedIn, String timeZone, UUID id);

    @Override
    Page<User> findAll(Pageable pageable);
}
