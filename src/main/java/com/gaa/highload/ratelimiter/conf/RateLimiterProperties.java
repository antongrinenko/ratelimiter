package com.gaa.highload.ratelimiter.conf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("rate.limit")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateLimiterProperties {
    private Long windowTimeSec;
    private Long windowMaxRequests;
}
