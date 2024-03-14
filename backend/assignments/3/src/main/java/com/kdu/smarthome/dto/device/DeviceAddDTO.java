package com.kdu.smarthome.dto.device;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceAddDTO {
    private String houseId;
    private String roomId;
    private String kickstonId;
}
