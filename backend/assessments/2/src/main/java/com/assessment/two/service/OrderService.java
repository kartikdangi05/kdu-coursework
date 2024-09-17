package com.assessment.two.service;

import com.assessment.two.entity.Order;
import com.assessment.two.entity.ShoppingCart;
import com.assessment.two.entity.Users;
import com.assessment.two.repo.OrderRepo;
import com.assessment.two.utils.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    public void placeOrder(ShoppingCart cart, Users user){
        Order order = OrderUtil.get(cart,user);
        orderRepo.save(order);
    }
}
