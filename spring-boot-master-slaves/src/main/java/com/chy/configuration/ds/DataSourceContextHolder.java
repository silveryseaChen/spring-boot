package com.chy.configuration.ds;

import com.chy.configuration.ds.slave.ISwitchRule;
import com.chy.configuration.ds.slave.SlaveSwitchFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chy on 2018/4/9.
 */
@Slf4j
public class DataSourceContextHolder {

    public static final String MasterDataSource = "masterDataSource";
    public static final String SlaveDataSource = "slaveDataSource";
    public static List<String> slaveDataSourceNames = new ArrayList<>();

    public static String slaveSwitchRule = "random";

    private static final ThreadLocal<String> dataSourceKey = new InheritableThreadLocal<String>();

    public static void setDataSourceKey(String dataSource) {
        dataSourceKey.set(getDataSourceKey(dataSource));
        log.info("数据源切换至>>>{}", dataSourceKey.get());
    }

    public static String getCurrDataSourceKey() {
        String ds = getDataSourceKey(dataSourceKey.get());
        log.info("当前数据源>>>{}", ds);
        return ds;
    }

    public static String getDataSourceKey(String ds) {

        String targetDataSource = null; //默认主库

        if(SlaveDataSource.equals(ds)){ //从库
            targetDataSource = getSlaveDataSource();
        } else {
            targetDataSource = ds;
        }

        if(targetDataSource == null){ //默认主库
            targetDataSource = MasterDataSource;
        }

        return targetDataSource;
    }

    /**默认切换规则*/
    public static String getSlaveDataSource(){

        ISwitchRule iSwitchRule = SlaveSwitchFactory.getSwitchRule(slaveSwitchRule);
        if(iSwitchRule == null){
            log.info(" there is no rule named {}, no slave rule selected", slaveSwitchRule);
            iSwitchRule = SlaveSwitchFactory.getSwitchRule("random");
        }

        return  iSwitchRule.getSlaveDataSource();
    }

    public static void changeToMaster() {
        setDataSourceKey(MasterDataSource);
    }

    public static void changeToSlave() {
        setDataSourceKey(SlaveDataSource);
    }

    public static void cleanDataSource(){
        dataSourceKey.remove();
    }

}
