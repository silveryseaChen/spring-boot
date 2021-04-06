package com.chy.configure;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * my health
 * Created by chy on 21/4/5.
 */
@Component
public class MyHealth implements HealthIndicator {
    @Override
    public Health health() {
        return Health.up().withDetail("my-health", "OK").build();
    }
}
