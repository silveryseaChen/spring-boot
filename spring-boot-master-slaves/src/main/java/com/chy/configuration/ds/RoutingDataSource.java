package com.chy.configuration.ds;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by chy on 2018/4/9.
 */
public class RoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getCurrDataSourceKey();
    }

}
