package com.springboot.datasource.config.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MasterSlaveRoutingDataSource extends AbstractRoutingDataSource  {
    @Override
    protected Object determineCurrentLookupKey() {
//        return DbContextHolder.getDbType();
        return DbContextHolder.getDb();
    }
}
