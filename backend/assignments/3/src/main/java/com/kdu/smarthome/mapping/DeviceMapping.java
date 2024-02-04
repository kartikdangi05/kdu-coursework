package com.kdu.smarthome.mapping;

import com.kdu.smarthome.dto.device.DeviceRequestDTO;
import com.kdu.smarthome.entity.Device;
import org.springframework.stereotype.Component;

@Component
public class DeviceMapping {

    public Device deviceMapping(DeviceRequestDTO deviceRequestDTO){
        Device device = new Device();
        device.setDeviceUsername(deviceRequestDTO.getDeviceUsername());
        device.setDevicePassword(deviceRequestDTO.getDevicePassword());
        device.setKickstonId(deviceRequestDTO.getKickstonId());
        return device;
    }
}
