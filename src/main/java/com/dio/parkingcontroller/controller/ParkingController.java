package com.dio.parkingcontroller.controller;

import com.dio.parkingcontroller.entity.dto.ParkingDTO;
import com.dio.parkingcontroller.controller.mapper.ParkingMapper;
import com.dio.parkingcontroller.entity.Parking;
import com.dio.parkingcontroller.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;
    @Autowired
    private ParkingMapper parkingMapper;

    @GetMapping("/todos")
    public ResponseEntity<List<ParkingDTO>> getAll(){
        List<Parking> parkings = parkingService.getAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkings);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDTO> getById(@PathVariable Long id){
        Parking parking = parkingService.getById(id);
        ParkingDTO result = parkingMapper.parkingDTO(parking);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/inserir")
    public ResponseEntity<ParkingDTO> insertNew(@RequestBody ParkingDTO parkingDTO){
        ParkingDTO savedParking =  parkingService.insertNew(parkingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedParking);
    }

}
