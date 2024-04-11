package com.dio.parkingcontroller.controller;

import com.dio.parkingcontroller.controller.dto.ParkingDTO;
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
        Parking parking = parkingMapper.toParking(parkingDTO);
        Parking savedParking = parkingService.insertNew(parking);
        ParkingDTO result = parkingMapper.toParkingDTO(savedParking);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id){
        Boolean flag = parkingService.deleteById(id);
        return ResponseEntity.ok().body(flag);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingDTO> update(@PathVariable Long id, @RequestBody ParkingDTO parkingDTO){
        Parking parking = parkingMapper.toParking(parkingDTO);
        parking = parkingService.update(id, parking);
        parkingDTO = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.ok().body(parkingDTO);
    }
}
