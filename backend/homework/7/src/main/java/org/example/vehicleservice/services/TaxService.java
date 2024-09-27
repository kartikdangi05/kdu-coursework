package org.example.vehicleservice.services;

import org.example.vehicleservice.constant.Constant;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class TaxService {
    public double getTax(String location){
        if(location.equals(Constant.Factory1)){
            return 0.18;
        } else if (location.equals(Constant.Factory2)) {
            return 0.15;
        }else {
            throw new IllegalArgumentException("No such location");
        }
    }
}
