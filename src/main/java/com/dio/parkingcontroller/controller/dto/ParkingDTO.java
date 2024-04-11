package com.dio.parkingcontroller.controller.dto;

import com.dio.parkingcontroller.entity.Parking;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParkingDTO extends Parking {
}
