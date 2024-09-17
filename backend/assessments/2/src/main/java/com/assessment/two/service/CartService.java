package com.assessment.two.service;

import com.assessment.two.entity.Items;
import com.assessment.two.entity.ShoppingCart;
import com.assessment.two.entity.Users;
import com.assessment.two.repo.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepo cartRepo;

    public void addInCart(List<Items> items, Users user){
        ShoppingCart cart = new ShoppingCart();
        cart.setItems(items);
        cart.setUser(user);
        cartRepo.save(cart);
    }

    public ShoppingCart getCart(Users user){
        return cartRepo.findByUserId(user.getId());
    }
}
