package com.kdu.smarthome.services;

import com.kdu.smarthome.dto.room.RoomResponseDTO;
import com.kdu.smarthome.entity.House;
import com.kdu.smarthome.entity.Room;
import com.kdu.smarthome.exceptions.NotFoundException;
import com.kdu.smarthome.mapping.RoomMapping;
import com.kdu.smarthome.repository.HouseRepository;
import com.kdu.smarthome.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final HouseRepository houseRepository;
    private final RoomMapping roomMapping;

    @Autowired
    public RoomService(RoomRepository roomRepository,
                       HouseRepository houseRepository,
                       RoomMapping roomMapping) {
        this.roomRepository = roomRepository;
        this.houseRepository = houseRepository;
        this.roomMapping = roomMapping;
    }

    /**
     * Add a room to the particular house using houseId
     * @param id
     * @param roomName
     * @return
     */
    public RoomResponseDTO addRoom(Long id, String roomName){

        Optional<House> optionalHouse = houseRepository.findById(id);
        if(optionalHouse.isPresent()){
            House house = optionalHouse.get();
            Room room = roomMapping.roomMapping(roomName,house);
            roomRepository.save(room);
            return new RoomResponseDTO("Room added successfully!",room, HttpStatus.OK);
        }
        else {
            throw new NotFoundException("House with given id not found!");
        }
    }
}
