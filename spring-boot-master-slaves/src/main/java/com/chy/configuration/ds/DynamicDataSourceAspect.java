package com.chy.configuration.ds;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by chy on 2018/4/9.
 */
@Aspect
@Component
@Slf4j
public class DynamicDataSourceAspect {

    @Before("@annotation(targetDataSource)")
    public void changeDataSource(JoinPoint point, TargetDataSource targetDataSource) throws Throwable {

        //如果不在我们注入的所有的数据源范围之内，那么输出警告信息，系统自动使用默认的数据源。
        if (targetDataSource == null) {
            log.info("数据源[{}]不存在，使用默认数据源: {} >{}", targetDataSource.value(), point.getSignature());
            DataSourceContextHolder.changeToMaster();
        } else {
            log.info("UseDataSource : {} > {}", targetDataSource.value(), point.getSignature());
            //找到的话，那么设置到动态数据源上下文中。
            DataSourceContextHolder.setDataSourceKey(targetDataSource.value());
        }

    }

    @After("@annotation(targetDataSource)")
    public void restoreDataSource(JoinPoint point, TargetDataSource targetDataSource) {
        log.info("RevertDataSource : {}", targetDataSource.value());
        DataSourceContextHolder.cleanDataSource();
    }

    @Before("@annotation(slaveDataSource)")
    public void changeDataSource(JoinPoint point, SlaveDataSource slaveDataSource) throws Throwable {
        log.info("切换至从库数据源");
        DataSourceContextHolder.changeToSlave();

    }

    @After("@annotation(slaveDataSource)")
    public void restoreDataSource(JoinPoint point, SlaveDataSource slaveDataSource) {
        log.info("RevertDataSource : slaveDataSource");
        DataSourceContextHolder.cleanDataSource();
    }

}
