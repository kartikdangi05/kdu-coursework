package com.assessment.two.repo;

import com.assessment.two.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users,Long> {

    @Query("SELECT u FROM Users u WHERE u.username = ?1")
    Users findUser(String username);
}
