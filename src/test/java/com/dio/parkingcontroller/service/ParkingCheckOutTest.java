package com.dio.parkingcontroller.service;

import com.dio.parkingcontroller.controller.dto.ParkingDTO;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingCheckOutTest {

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTest(){
        RestAssured.port = randomPort;
    }

    @Test
    void whenGetBillCheckResult() {

        ParkingDTO parkingDTOcobalt = new ParkingDTO();
        parkingDTOcobalt.setModel("Cobalt");
        parkingDTOcobalt.setColor("Preto");
        parkingDTOcobalt.setLicense("QOZ0H19");
        parkingDTOcobalt.setState("RJ");
        parkingDTOcobalt.setEntryDate(LocalDateTime.of(2024,4,12,8,0));

        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(parkingDTOcobalt)
                .post("parking/1/checkout")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }
}