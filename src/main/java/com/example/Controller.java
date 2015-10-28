package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import retrofit.Call;
import retrofit.Response;

import java.io.IOException;

@org.springframework.stereotype.Controller
public class Controller {
    private final RandomNumberService randomNumberService;


    @Autowired
    public Controller(RandomNumberService randomNumberService) {
        this.randomNumberService = randomNumberService;
    }

    @RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity randomNumber() throws IOException {
        Call<RandomNumberResponse> call = randomNumberService.getRandomNumbers(5);
        Response<RandomNumberResponse> response = call.execute();
        if (response.isSuccess()) {
            return ResponseEntity.ok(response.body());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response.raw().toString());
        }
    }

    @RequestMapping(value = "/bar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity bar() throws IOException {
        Call<InvalidRandomNumberResponse> call = randomNumberService.getInvalidRandomNumbers(5);
        Response<InvalidRandomNumberResponse> response = call.execute();
        if (response.isSuccess()) {
            return ResponseEntity.ok(response.body());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response.raw().toString());
        }
    }

    @RequestMapping(value = "/foo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity foo() throws IOException {
        Response<RandomNumberResponse> response = randomNumberService.getFoo().execute();
        if (response.isSuccess()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response.raw().toString());
        }
    }
}
