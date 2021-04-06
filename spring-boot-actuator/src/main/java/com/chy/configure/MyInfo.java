package com.chy.configure;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

/**
 * my info
 * Created by chy on 21/4/3.
 */
@Component
public class MyInfo implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("my-info", "my actuator info");
    }

}
