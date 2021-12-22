package com.gaa.highload.ratelimiter.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest()
public class DataControllerTest {
    @Autowired
    private DataController dataController;

    @Test
    public void getLimitedRequestDataTest() {
        assertEquals(HttpStatus.OK, dataController.getLimitedRequestData().getStatusCode());
        assertEquals(HttpStatus.OK, dataController.getLimitedRequestData().getStatusCode());
        assertEquals(HttpStatus.OK, dataController.getLimitedRequestData().getStatusCode());
        assertEquals(HttpStatus.TOO_MANY_REQUESTS, dataController.getLimitedRequestData().getStatusCode());
        assertEquals(HttpStatus.TOO_MANY_REQUESTS, dataController.getLimitedRequestData().getStatusCode());
    }

    @Test
    public void getUnlimitedRequestDataTest() {
        assertEquals(HttpStatus.OK, dataController.getUnlimitedRequestData().getStatusCode());
        assertEquals(HttpStatus.OK, dataController.getUnlimitedRequestData().getStatusCode());
        assertEquals(HttpStatus.OK, dataController.getUnlimitedRequestData().getStatusCode());
        assertEquals(HttpStatus.OK, dataController.getUnlimitedRequestData().getStatusCode());
        assertEquals(HttpStatus.OK, dataController.getUnlimitedRequestData().getStatusCode());
    }
}
