package com.chy.configuration.ds;

import java.lang.annotation.*;

/**
 * Created by chy on 2018/4/9.
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {

    String value();

}
