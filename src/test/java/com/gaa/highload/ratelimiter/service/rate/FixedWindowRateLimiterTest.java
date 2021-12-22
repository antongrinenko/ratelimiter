package com.gaa.highload.ratelimiter.service.rate;

import com.gaa.highload.ratelimiter.conf.RateLimiterProperties;
import com.gaa.highload.ratelimiter.service.TimeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FixedWindowRateLimiterTest {
    @Test
    public void shouldReturnTrueOnlyForFirstThreeRequestsPerSameTimeWindow() {
        TimeService timeService = Mockito.mock(TimeService.class);
        RateLimiterProperties rateLimiterProperties = new RateLimiterProperties(60L, 3L);
        RateLimiter rateLimiter = new FixedWindowRateLimiter(rateLimiterProperties, timeService);
        long initialTime = 1640037600000L;  //2021-12-21 00:00:00

        Mockito.when(timeService.getCurrentTime()).thenReturn(initialTime);
        assertTrue(rateLimiter.isAllowed("key1")); // key1 - 1 request

        Mockito.when(timeService.getCurrentTime()).thenReturn(initialTime + 20 * 1000); // initial time plus 20 sec
        assertTrue(rateLimiter.isAllowed("key1")); // key1 - 2 request

        Mockito.when(timeService.getCurrentTime()).thenReturn(initialTime + 40 * 1000); // initial time plus 40 sec
        assertTrue(rateLimiter.isAllowed("key1")); // key1 - 3 request
        assertTrue(rateLimiter.isAllowed("key2")); // key2 - 1 request

        Mockito.when(timeService.getCurrentTime()).thenReturn(initialTime + 59 * 1000); // initial time plus 59 sec
        assertFalse(rateLimiter.isAllowed("key1")); // key1 - 4 request
        assertTrue(rateLimiter.isAllowed("key2"));  // key2 - 2 request
        assertTrue(rateLimiter.isAllowed("key2"));  // key2 - 3 request
        assertFalse(rateLimiter.isAllowed("key2")); // key2 - 4 request

        Mockito.when(timeService.getCurrentTime()).thenReturn(initialTime + 60 * 1000); // initial time plus 60 sec
        assertTrue(rateLimiter.isAllowed("key1"));  // key1 - 1 request
        assertTrue(rateLimiter.isAllowed("key2"));  // key2 - 2 request
    }
}
