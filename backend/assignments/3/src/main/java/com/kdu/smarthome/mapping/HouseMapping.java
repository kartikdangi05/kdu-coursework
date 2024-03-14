package com.kdu.smarthome.mapping;

import com.kdu.smarthome.dto.house.HouseRequestDTO;
import com.kdu.smarthome.entity.House;
import com.kdu.smarthome.entity.UserModel;
import org.springframework.stereotype.Component;

@Component
public class HouseMapping {

    public House houseMapping(HouseRequestDTO houseRequestDTO, UserModel userModel){
        House house = new House();
        house.setHouseName(houseRequestDTO.getHouseName());
        house.setAddress(houseRequestDTO.getAddress());
        house.getUsersList().add(userModel);
        return house;
    }
}
