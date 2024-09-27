package com.example.demo.services;

import com.example.demo.dto.VehicleRequestDTO;
import com.example.demo.dto.VehicleResponseDTO;
import com.example.demo.exceptions.EmptyListException;
import com.example.demo.exceptions.InvalidVehicleRequestException;
import com.example.demo.exceptions.VehicleNotFoundException;
import com.example.demo.models.Vehicle;
import com.example.demo.repository.VehicleRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    private static final Logger logger = LoggerFactory.getLogger(VehicleService.class);
    @Autowired
    VehicleRepo vehicleRepo;

    /**
     * Adding a vehicle to the repository
     * @param vehicleRequestDTO -> vehicle object to be added
     * @return A response DTO with the object of vehicle we have created and its id
     */
    public VehicleResponseDTO addVehicle(VehicleRequestDTO vehicleRequestDTO){
        if (vehicleRequestDTO == null || vehicleRequestDTO.getName().isEmpty()
                || vehicleRequestDTO.getFactory().isEmpty()
                || vehicleRequestDTO.getTyre() == null
                || vehicleRequestDTO.getSpeaker() == null
                || vehicleRequestDTO.getPrice() <= 0) {
            throw new InvalidVehicleRequestException("Invalid POST format");
        }

        Vehicle newVehicle = new Vehicle(vehicleRequestDTO.getName(), vehicleRequestDTO.getFactory(),
                vehicleRequestDTO.getTyre(), vehicleRequestDTO.getSpeaker(), vehicleRequestDTO.getPrice()
                + vehicleRequestDTO.getTyre().getPrice() + vehicleRequestDTO.getSpeaker().getPrice());

        vehicleRepo.createVehicle(newVehicle);
        logger.info("Vehicle added successfully!");
        return new VehicleResponseDTO(newVehicle,"Vehicle added successfully! ID : ".concat(Long.toString(newVehicle.getId())),true);

    }

    /**
     * Deleting a vehicle from the repository
     * @param vehicleId -> vehicle id
     * @return String showing successful deletion
     */
    public VehicleResponseDTO deleteVehicle(Long vehicleId){
        Vehicle vehicle = vehicleRepo.deleteVehicle(vehicleId);
        if(vehicle == null){
            logger.error("Vehicle with given id is not present");
            throw new VehicleNotFoundException("Vehicle with given id is not present");
        }
        logger.info("Vehicle deleted successfully!");
        return new VehicleResponseDTO(vehicle,"Vehicle deleted successfully!",true);
    }

    /**
     * Getting a vehicle object by id
     * @param vehicleId -> vehicle id
     * @return a vehicle object we requested
     */
    public VehicleResponseDTO getVehicle(Long vehicleId){
        Vehicle vehicle = vehicleRepo.getVehicleById(vehicleId);
        if(vehicle == null){
            logger.error("Vehicle with given id is not present");
            throw new VehicleNotFoundException("Vehicle with given id is not present");
        }
        logger.info("Vehicle found successfully!");
        return new VehicleResponseDTO(vehicle,"Vehicle found successfully!",true);
    }

    /**
     * Updating the vehicle specified using its id
     * @param id -> id to be updated
     * @param vehicleRequestDTO -> vehicle object
     * @return updated vehicle object
     */
    public VehicleResponseDTO updateVehicle(Long id, VehicleRequestDTO vehicleRequestDTO){
        Vehicle vehicle = vehicleRepo.updateVehicle(id,vehicleRequestDTO);
        if(vehicle == null){
            logger.error("Vehicle with given id is not present");
            throw new VehicleNotFoundException("Vehicle with given id is not present");
        }
        logger.info("Vehicle Updated successfully!");
        return new VehicleResponseDTO(vehicle, "Vehicle Updated successfully!",true);
    }

    /**
     * Getting most and least expensive vehicle based on choice
     * @param choice -> can be most and least
     * @return -> most or least expensive vehicle based on choice
     */
    public VehicleResponseDTO getMostAndLeastExpVehicle(String choice){
        if(choice.equals("most")){
            Vehicle vehicle = vehicleRepo.mostExpVehicle();
            if(vehicle == null){
                logger.warn("Vehicles list is empty!");
                throw new EmptyListException("Vehicles list is empty!");
            }
            logger.info("Most Expensive vehicle Fetched successfully!");
            return new VehicleResponseDTO(vehicle,"Here is the most expensive vehicle!",true);
        }
        else{
            Vehicle vehicle = vehicleRepo.leastExpVehicle();
            if(vehicle == null){
                logger.warn("Vehicles list is empty!");
                throw new EmptyListException("Vehicles list is empty!");
            }
            logger.info("Least Expensive vehicle Fetched successfully!");
            return new VehicleResponseDTO(vehicle,"Here is the least expensive vehicle",true);
        }
    }
}
