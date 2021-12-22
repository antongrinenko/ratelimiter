package com.gaa.highload.ratelimiter.service.rate;

import com.gaa.highload.ratelimiter.conf.RateLimiterProperties;
import com.gaa.highload.ratelimiter.service.TimeService;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


@RequiredArgsConstructor
public class FixedWindowRateLimiter implements RateLimiter {
    private final RateLimiterProperties rateLimiterProperties;
    private final TimeService timeService;

    private Map<String, AtomicLong> rateLimiterMap = new ConcurrentHashMap<>();
    private volatile long activeWindowTimestamp;

    public boolean isAllowed(String requestKey) {
        long windowTimestamp = getCurrentWindowTimestamp();
        if (activeWindowTimestamp != windowTimestamp) {
            activeWindowTimestamp = windowTimestamp;
            rateLimiterMap.clear();
        }

        rateLimiterMap.putIfAbsent(requestKey, new AtomicLong(0L));
        return rateLimiterMap.get(requestKey).incrementAndGet() <= rateLimiterProperties.getWindowMaxRequests();
    }

    private long getCurrentWindowTimestamp() {
        return timeService.getCurrentTime() / (rateLimiterProperties.getWindowTimeSec() * 1000);
    }
}
