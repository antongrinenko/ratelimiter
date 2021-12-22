package com.gaa.highload.ratelimiter.service;

import java.time.Instant;

public class TimeService {
    public long getCurrentTime() {
        return Instant.now().toEpochMilli();
    }
}
