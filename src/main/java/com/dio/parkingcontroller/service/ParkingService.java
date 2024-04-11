package com.dio.parkingcontroller.service;

import com.dio.parkingcontroller.entity.dto.ParkingDTO;
import com.dio.parkingcontroller.entity.Parking;
import com.dio.parkingcontroller.respository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingService {

    @Autowired
    private ParkingRepository parkingRepository;

    // Retorna lista com todos os parkings
    public List<Parking> getAll() {
        return parkingRepository.findAll();
    }

    // Retorna um parking específico
    public Parking getById(Long id) {
        Optional<Parking> parkingOptional = parkingRepository.findById(id);
        return parkingOptional.orElseThrow(() -> new RuntimeException("Parking not found with id: " + id));
    }

    // Insere um novo parking
    public ParkingDTO insertNew(ParkingDTO parkingDTO){
        parkingDTO.setEntryDate(LocalDateTime.now());
        return parkingRepository.save(parkingDTO);
    }
}
