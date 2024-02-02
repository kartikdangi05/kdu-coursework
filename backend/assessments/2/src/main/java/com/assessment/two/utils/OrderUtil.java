package com.assessment.two.utils;

import com.assessment.two.entity.*;
import java.time.LocalDate;
import java.util.List;

public class OrderUtil {

    private OrderUtil(){}

    public static Order get(ShoppingCart cart,Users user){
        LocalDate date = LocalDate.now();
        List<Items> productList = cart.getItems();

        int amount = 0;
        for(Items item : productList){
            int stock = item.getProduct().getStock();
            if(item.getProduct().getStock() <= item.getQty()){
                stock += item.getQty();
                item.getProduct().setStock(stock);
            }
            amount += item.getProduct().getPrice() * item.getQty();
            item.getProduct().setStock(stock - item.getQty());
        }

        Order order = new Order();
        order.setOrderDate(date);
        order.setAmount(amount);
        order.setUser(user);
        return order;
    }
}
