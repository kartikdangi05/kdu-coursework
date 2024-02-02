package com.assessment.two.repo;

import com.assessment.two.entity.Items;
import com.assessment.two.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<ShoppingCart,Long> {
    @Query("SELECT su FROM ShoppingCart su WHERE su.id = ?1")
    ShoppingCart findByUserId(Long id);
}
