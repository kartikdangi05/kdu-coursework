package com.kdu.smarthome.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kdu.smarthome.dto.*;
import com.kdu.smarthome.dto.house.HouseListDTO;
import com.kdu.smarthome.dto.house.HouseRequestDTO;
import com.kdu.smarthome.dto.house.HouseResponseDTO;
import com.kdu.smarthome.dto.room.RoomsDevicesDTO;
import com.kdu.smarthome.entity.Device;
import com.kdu.smarthome.entity.House;
import com.kdu.smarthome.entity.Room;
import com.kdu.smarthome.entity.UserModel;
import com.kdu.smarthome.exceptions.NotAuthorizedException;
import com.kdu.smarthome.exceptions.NotFoundException;
import com.kdu.smarthome.exceptions.UserNotAdminException;
import com.kdu.smarthome.mapping.HouseMapping;
import com.kdu.smarthome.repository.HouseRepository;
import com.kdu.smarthome.repository.UserRepository;
import com.kdu.smarthome.utils.JSONUtil;
import com.kdu.smarthome.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HouseService {
    private final HouseRepository houseRepository;
    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;
    private final HouseMapping houseMapping;
    private final JSONUtil jsonUtil;

    @Autowired
    public HouseService(HouseRepository houseRepository,
                        UserRepository userRepository,
                        JWTUtil jwtUtil,
                        HouseMapping houseMapping,
                        JSONUtil jsonUtil) {
        this.houseRepository = houseRepository;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.houseMapping = houseMapping;
        this.jsonUtil = jsonUtil;
    }

    /**
     * Adds a house to the user associated with the token.
     * @param houseRequestDTO
     * @param token
     * @return
     */
    public HouseResponseDTO addHouse(HouseRequestDTO houseRequestDTO, String token){
        String username = jwtUtil.decodeToken(token);
        Optional<UserModel> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isEmpty())
            throw new NotFoundException("User not found");

        UserModel user = optionalUser.get();
        user.setRole("ROLE_ADMIN");
        House house = houseMapping.houseMapping(houseRequestDTO,user);
        userRepository.save(user);
        houseRepository.save(house);
        return new HouseResponseDTO("House added successfully!",house, HttpStatus.OK);
    }

    /**
     * Adds a user to the house. Can only be added by admin
     * @param id
     * @param username
     * @param token
     * @return
     */
    public ResponseInfoDTO addUser(Long id, String username, String token){
        String userAdmin = jwtUtil.decodeToken(token);
        Optional<UserModel> optionalUser = userRepository.findByUsername(userAdmin);
        if(optionalUser.isEmpty())
            throw new NotAuthorizedException("User Not found");

        UserModel user = optionalUser.get();
        if(user.getRole().equals("ROLE_ADMIN")){
            Optional<House> optionalHouse = houseRepository.findById(id);
            if(optionalHouse.isPresent()){
                House house = optionalHouse.get();
                Optional<UserModel> optionalUserModel = userRepository.findByUsername(username);
                if(optionalUserModel.isEmpty())
                    throw new NotAuthorizedException("User Not found");


                house.getUsersList().add(optionalUserModel.get());
                return new ResponseInfoDTO("User added successfully!","Username : ".concat(username),HttpStatus.OK);
            }
            else {
                throw new NotFoundException("House with given id not found");
            }
        }
        else{
            throw new UserNotAdminException("Given user is not admin!");
        }
    }

    /**
     * Fetches all the houses
     * @return
     * @throws JsonProcessingException
     */
    public HouseListDTO getAll() throws JsonProcessingException {
        List<House> houseList = houseRepository.findAll();
        String houses = jsonUtil.convertListToJSONString(houseList);
        return new HouseListDTO("Fetched Successfully!",houses,HttpStatus.OK);
    }

    /**
     * Updates the address of a house
     * @param id
     * @param newAddress
     * @return
     */
    public ResponseInfoDTO updateAddress(Long id, String newAddress){
        Optional<House> optHouse = houseRepository.findById(id);
        if(optHouse.isPresent()){
            House house = optHouse.get();
            house.setAddress(newAddress);
            houseRepository.save(house);
            return new ResponseInfoDTO("House updated successfully!","New Address : ".concat(newAddress),HttpStatus.OK);
        }
        else{
            throw new NotFoundException("House with given id not found");
        }
    }

    /**
     * Get details of the house along with its room and its devices
     * @param id
     * @return
     * @throws JsonProcessingException
     */
    public RoomsDevicesDTO getRoomsDevices(Long id) throws JsonProcessingException {
        Optional<House> optionalHouse = houseRepository.findById(id);
        if(optionalHouse.isPresent()){
            House house = optionalHouse.get();
            List<Room> rooms = house.getRooms();
            List<Device> devices = new ArrayList<>();
            for(Room room : rooms){
                List<Device> deviceList = room.getDeviceList();
                devices.addAll(deviceList);
            }
            TempRespDTO tempRespDTO = new TempRespDTO(house,rooms,devices);
            String roomsDevices = jsonUtil.convertObjToJSONString(tempRespDTO);
            return new RoomsDevicesDTO("Fetched successfully!",roomsDevices,HttpStatus.OK);
        }
        else{
            throw new NotFoundException("House with given id not found!");
        }

    }
}
