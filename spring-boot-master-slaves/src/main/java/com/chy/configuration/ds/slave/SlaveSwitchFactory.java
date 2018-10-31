package com.chy.configuration.ds.slave;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by chy on 2018/9/11.
 */
@Component
@Slf4j
public class SlaveSwitchFactory {

    private static Map<String, ISwitchRule> rules = new ConcurrentHashMap<>();

    //自动注入 所有切换规则
    @Autowired
    public SlaveSwitchFactory(Map<String, ISwitchRule> rules){
        this.rules = rules;
    }

    public static ISwitchRule getSwitchRule(String rule){
        log.info(" start to select {} rule", rule);
        return rules.get(rule);
    }

}
