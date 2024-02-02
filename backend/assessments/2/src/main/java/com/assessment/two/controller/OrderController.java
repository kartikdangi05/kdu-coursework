package com.assessment.two.controller;

import com.assessment.two.entity.ShoppingCart;
import com.assessment.two.entity.Users;
import com.assessment.two.repo.UserRepo;
import com.assessment.two.service.OrderService;
import com.assessment.two.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/place")
    public ResponseEntity<String> placeOrder(@RequestBody ShoppingCart cart){
        String username = jwtUtil.getUser();
        Users user = userRepo.findUser(username);
        orderService.placeOrder(cart,user);
        return new ResponseEntity<>("Order placed successfully!", HttpStatus.OK);
    }

}
