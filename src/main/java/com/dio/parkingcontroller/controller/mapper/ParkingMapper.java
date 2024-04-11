package com.dio.parkingcontroller.controller.mapper;

import com.dio.parkingcontroller.entity.dto.ParkingDTO;
import com.dio.parkingcontroller.entity.Parking;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParkingMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public ParkingDTO parkingDTO(Parking parking){
        return MODEL_MAPPER.map(parking, ParkingDTO.class);
    }

    public List<ParkingDTO> toParkingDTOList(List<Parking> parkings){
        return parkings.stream().map(this::parkingDTO).collect(Collectors.toList());
    }

    public Parking toParkingDTO (ParkingDTO parking) {
        return MODEL_MAPPER.map(parking, Parking.class);
    }

    public Parking toParking(ParkingDTO parkingDTO) {
        return MODEL_MAPPER.map(parkingDTO, Parking.class);
    }
}
