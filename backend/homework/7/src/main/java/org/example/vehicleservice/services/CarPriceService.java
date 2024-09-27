package org.example.vehicleservice.services;

import org.example.vehicleservice.constant.Constant;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class CarPriceService {
    public double getPrice(String location){
        if(location.equals(Constant.Factory1)){
            return 400000.0;
        } else if (location.equals(Constant.Factory2)) {
            return 440000.0;
        } else {
            throw new IllegalArgumentException("No such location");
        }
    }
}
