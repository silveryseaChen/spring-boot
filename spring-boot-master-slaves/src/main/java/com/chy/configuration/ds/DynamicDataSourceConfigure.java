package com.chy.configuration.ds;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chy on 2018/4/9.
 */
@Configuration
@Slf4j
public class DynamicDataSourceConfigure {

    /**
     * 支持自定义扩展 切换规则
     * 实现接口com.chy.configuration.ds.slave.ISwitchRule
     * 会自动注入 传入component values 即可
     */
    @Value("${ds.slave.switch-rule}")
    private String slaveSwitchRule;

    @Bean
    @Primary
    @Qualifier("masterDataSourceProperties")
    @ConfigurationProperties(prefix = "ds.master")
    public DataSourceProperties masterDataSourceProperties() {
        log.info("init master DataSourceProperties>>>>>");
        return new DataSourceProperties();
    }

    @Bean
    @Qualifier("slave1DataSourceProperties")
    @ConfigurationProperties(prefix = "ds.slave")
    public List<DataSourceProperties> slaveDataSourceProperties() {
        log.info("init slave DataSourceProperties>>>>>");
        return new ArrayList<DataSourceProperties>();
    }

    @Primary
    @Bean(name = "dynamicDataSource")
    @Order(5)
    public AbstractRoutingDataSource dynamicDataSource() {

        // 表示可用的数据源,包括写和读数据源
        Map<Object, Object> targetDataSources = new HashMap<>();

        DataSource masterDataSource = masterDataSourceProperties().initializeDataSourceBuilder().type(DruidDataSource.class).build();
        //写
        targetDataSources.put(DataSourceContextHolder.MasterDataSource, masterDataSource);

        List<DataSourceProperties> slaves = slaveDataSourceProperties();
        if(slaves != null && !slaves.isEmpty()){
            for (int i = 0; i < slaves.size(); i++) {
                DataSource slaveDataSource = slaves.get(i).initializeDataSourceBuilder().type(DruidDataSource.class).build();
                String slaveName = DataSourceContextHolder.SlaveDataSource+"_"+i;
                DataSourceContextHolder.slaveDataSourceNames.add(slaveName);

                targetDataSources.put(slaveName, slaveDataSource);
            }
        }

        //从库切换规则
        DataSourceContextHolder.slaveSwitchRule = slaveSwitchRule;
        // 设置默认的数据源为写数据源
        RoutingDataSource proxy = new RoutingDataSource();
        proxy.setDefaultTargetDataSource(masterDataSource);
        proxy.setTargetDataSources(targetDataSources);
        return proxy;
    }


}
