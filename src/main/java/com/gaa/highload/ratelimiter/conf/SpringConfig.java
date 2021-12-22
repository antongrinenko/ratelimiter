package com.gaa.highload.ratelimiter.conf;

import com.gaa.highload.ratelimiter.service.TimeService;
import com.gaa.highload.ratelimiter.service.rate.FixedWindowRateLimiter;
import com.gaa.highload.ratelimiter.service.rate.RateLimiter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({RateLimiterProperties.class})
public class SpringConfig {
	@Bean
	public RateLimiter rateLimiter(RateLimiterProperties rateLimiterProperties, TimeService timeService) {
		return new FixedWindowRateLimiter(rateLimiterProperties, timeService);
	}

	@Bean
	public TimeService timeService() {
		return new TimeService();
	}
}
