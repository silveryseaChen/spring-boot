package com.chy.configure;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * my metrics
 * Created by chy on 21/4/5.
 */
@Component
public class MyMetricks {

    private final MeterRegistry meterRegistry;

    public MyMetricks(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public void regist(){
        meterRegistry.counter("order", "add", "count").increment();
    }
}
