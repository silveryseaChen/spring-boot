package com.chy.configuration.ds.slave.impl;

import com.chy.configuration.ds.DataSourceContextHolder;
import com.chy.configuration.ds.slave.ISwitchRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by chy on 2018/9/11.
 * 顺序
 */
@Component("order")
@Slf4j
public class OrderSwitchRule implements ISwitchRule {

    public static Integer slaveIndex = -1;

    @Override
    public String getSlaveDataSource() {

        slaveIndex++;
        if(slaveIndex >= DataSourceContextHolder.slaveDataSourceNames.size()){
            slaveIndex = 0;
        }
        String slave = DataSourceContextHolder.slaveDataSourceNames.get(slaveIndex);

        return slave;
    }
}
