package com.dio.parkingcontroller.controller;

import com.dio.parkingcontroller.controller.dto.ParkingDTO;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerTest {

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void serUpTest(){
        RestAssured.port = randomPort;
    }

    @Test
    void whenGetAllCheckResult() {

        RestAssured.given()
                .when()
                .get("parking/todos")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void whenInsertNewThenCheckIsInserted() {

        ParkingDTO parkingDTOcobalt = new ParkingDTO();
        parkingDTOcobalt.setModel("Cobalt");
        parkingDTOcobalt.setColor("Preto");
        parkingDTOcobalt.setLicense("QOZ0H19");
        parkingDTOcobalt.setState("RJ");


        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(parkingDTOcobalt)
                .post("parking/inserir")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("license", Matchers.equalTo("QOZ0H19"));
    }
}