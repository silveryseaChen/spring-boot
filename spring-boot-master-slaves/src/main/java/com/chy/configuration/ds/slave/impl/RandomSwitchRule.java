package com.chy.configuration.ds.slave.impl;

import com.chy.configuration.ds.DataSourceContextHolder;
import com.chy.configuration.ds.slave.ISwitchRule;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by chy on 2018/9/11.
 * 随机
 */
@Component("random")
public class RandomSwitchRule implements ISwitchRule {

    @Override
    public String getSlaveDataSource() {
        int random = new Random().nextInt(DataSourceContextHolder.slaveDataSourceNames.size());
        String slave = DataSourceContextHolder.slaveDataSourceNames.get(random);
        return slave;
    }

}
