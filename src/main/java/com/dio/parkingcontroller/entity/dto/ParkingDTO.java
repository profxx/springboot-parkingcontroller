package com.dio.parkingcontroller.entity.dto;

import com.dio.parkingcontroller.entity.Parking;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParkingDTO extends Parking {
}
