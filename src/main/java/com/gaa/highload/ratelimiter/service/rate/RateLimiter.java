package com.gaa.highload.ratelimiter.service.rate;

public interface RateLimiter {
    boolean isAllowed(String key);
}
