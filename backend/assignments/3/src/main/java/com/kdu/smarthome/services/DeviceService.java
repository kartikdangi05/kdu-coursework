package com.kdu.smarthome.services;

import com.kdu.smarthome.dto.device.DeviceAddDTO;
import com.kdu.smarthome.dto.device.DeviceRequestDTO;
import com.kdu.smarthome.dto.ResponseInfoDTO;
import com.kdu.smarthome.entity.Device;
import com.kdu.smarthome.entity.House;
import com.kdu.smarthome.entity.Inventory;
import com.kdu.smarthome.entity.Room;
import com.kdu.smarthome.exceptions.IncorrectUsernamePasswordException;
import com.kdu.smarthome.exceptions.NotFoundException;
import com.kdu.smarthome.mapping.DeviceMapping;
import com.kdu.smarthome.repository.DeviceRepository;
import com.kdu.smarthome.repository.HouseRepository;
import com.kdu.smarthome.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final InventoryRepository inventoryRepository;
    private final DeviceMapping deviceMapping;
    private final HouseRepository houseRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository,
                         InventoryRepository inventoryRepository,
                         DeviceMapping deviceMapping,
                         HouseRepository houseRepository) {
        this.deviceRepository = deviceRepository;
        this.inventoryRepository = inventoryRepository;
        this.deviceMapping = deviceMapping;
        this.houseRepository = houseRepository;
    }

    public ResponseInfoDTO registerDevice(DeviceRequestDTO deviceRequestDTO){
        Optional<Inventory> optionalInventory = inventoryRepository.findByKickstonId(deviceRequestDTO.getKickstonId());
        if(optionalInventory.isPresent() && optionalInventory.get().getDeviceUsername().equals(deviceRequestDTO.getDeviceUsername())){
            Inventory inventory = optionalInventory.get();
            if(deviceRequestDTO.getDevicePassword().equals(inventory.getDevicePassword())){
                Device device = deviceMapping.deviceMapping(deviceRequestDTO);
                deviceRepository.save(device);
                return new ResponseInfoDTO("Device registered successfully!","Kickston id : ".concat(device.getKickstonId()), HttpStatus.OK);
            }
            else{
                throw new IncorrectUsernamePasswordException("Incorrect password!");
            }
        }
        else{
            throw new NotFoundException("Device not found in inventory!");
        }
    }

    public ResponseInfoDTO addDevice(DeviceAddDTO deviceAddDTO){
        Optional<Device> optionalDevice = deviceRepository.findByKickstonId(deviceAddDTO.getKickstonId());
        Optional<House> optionalHouse = houseRepository.findById(Long.parseLong(deviceAddDTO.getHouseId()));

        if(optionalHouse.isPresent() && optionalDevice.isPresent()){
            House house = optionalHouse.get();
            Device device = optionalDevice.get();

            Long roomId = Long.parseLong(deviceAddDTO.getRoomId());
            Optional<Room> optionalRoom = house.getRooms().stream()
                    .filter(room -> room.getId().equals(roomId))
                    .findFirst();

            if (optionalRoom.isPresent()) {
                Room room = optionalRoom.get();
                device.setRoom(room);
                deviceRepository.save(device);
                return new ResponseInfoDTO("Device added successfully!", "Room: ".concat(deviceAddDTO.getRoomId()), HttpStatus.OK);
            } else {
                throw new NotFoundException("Room with the given id not found!");
            }

        }
        else{
            if(optionalDevice.isEmpty() && optionalHouse.isEmpty())
                throw new NotFoundException("House and Device with given id not found!");
            else if (optionalHouse.isEmpty()) {
                throw new NotFoundException("House with given id not found!");
            }
            else{
                throw new NotFoundException("Device with given id not found!");
            }
        }

    }
}

