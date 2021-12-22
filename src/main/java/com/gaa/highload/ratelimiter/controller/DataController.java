package com.gaa.highload.ratelimiter.controller;

import com.gaa.highload.ratelimiter.service.rate.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DataController {
    private static final String LIMITED_URL = "/limited";
    private static final String UNLIMITED_URL = "/unlimited";

    private final RateLimiter rateLimiter;

    @GetMapping(LIMITED_URL)
    public ResponseEntity<String> getLimitedRequestData() {
        return rateLimiter.isAllowed(LIMITED_URL)
                ? ResponseEntity.ok("Success")
                : ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    @GetMapping(UNLIMITED_URL)
    public ResponseEntity<String> getUnlimitedRequestData() {
        return ResponseEntity.ok("Success");
    }
}
