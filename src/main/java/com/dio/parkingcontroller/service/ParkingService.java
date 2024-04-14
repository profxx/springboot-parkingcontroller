package com.dio.parkingcontroller.service;

import com.dio.parkingcontroller.controller.dto.ParkingDTO;
import com.dio.parkingcontroller.entity.Parking;
import com.dio.parkingcontroller.exception.ParkingNotFoundException;
import com.dio.parkingcontroller.respository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParkingService {

    @Autowired
    private ParkingRepository parkingRepository;


    // Retorna lista com todos os parkings
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Parking> getAll() {
        return parkingRepository.findAll();
    }

    // Retorna um parking específico
    @Transactional(readOnly = true)
    public Parking getById(Long id) {
        return parkingRepository.findById(id).orElseThrow(() -> new ParkingNotFoundException("Parking not found with id: " + id));
    }

    // Insere um novo parking
    @Transactional
    public Parking insertNew(Parking parking){
        parking.setEntryDate(LocalDateTime.now());
        return parkingRepository.save(parking);
    }

    // Altera um parking
    @Transactional
    public Parking update(Long id, Parking parking){
        Parking currentParking = getById(id);
        currentParking.setColor(parking.getColor());
        currentParking.setModel(parking.getModel());
        currentParking.setState(parking.getState());
        currentParking.setLicense(parking.getLicense());
        parkingRepository.save(currentParking);
        return currentParking;
    }

    // Deleta um parking
    @Transactional
    public Boolean deleteById(Long id){
        Parking parking = getById(id);
        if (parking == null){
            return false;
        }else {
            parkingRepository.deleteById(id);
            return true;
        }
    }

    // Registra a saída de um veículo
    @Transactional
    public Parking checkOut(Long id){
        Parking parking = getById(id);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(ParkingCheckOut.getBill(parking));
        parkingRepository.save(parking);
        return parking;
        }
}
