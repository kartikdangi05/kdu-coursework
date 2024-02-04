package com.kdu.smarthome.mapping;

import com.kdu.smarthome.entity.House;
import com.kdu.smarthome.entity.Room;
import org.springframework.stereotype.Component;

@Component
public class RoomMapping {

    public Room roomMapping(String roomName, House house){
        Room room = new Room();
        room.setRoomName(roomName);
        room.setHouse(house);
        return room;
    }
}
