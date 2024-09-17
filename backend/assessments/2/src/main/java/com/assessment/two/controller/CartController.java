package com.assessment.two.controller;

import com.assessment.two.entity.Items;
import com.assessment.two.entity.Product;
import com.assessment.two.entity.ShoppingCart;
import com.assessment.two.entity.Users;
import com.assessment.two.repo.UserRepo;
import com.assessment.two.service.CartService;
import com.assessment.two.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/add")
    public ResponseEntity<String> addInCart(@RequestBody List<Items> items){
        String username = jwtUtil.getUser();
        Users user = userRepo.findUser(username);
        cartService.addInCart(items,user);
        return new ResponseEntity<>("Product successfully added in cart", HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<ShoppingCart> getCart(){
        String username = jwtUtil.getUser();
        Users user = userRepo.findUser(username);
        ShoppingCart cart = cartService.getCart(user);
        return new ResponseEntity<>(cart,HttpStatus.OK);
    }

}
