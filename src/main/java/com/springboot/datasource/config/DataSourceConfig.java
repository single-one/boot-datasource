package com.springboot.datasource.config;

import com.springboot.datasource.config.datasource.DbContextHolder;
import com.springboot.datasource.config.datasource.MasterSlaveRoutingDataSource;
import com.springboot.datasource.enums.DbEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {
    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean(name = "dbMasterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource dbTestDataSource(){
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "dbSlaveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource dbSlaveDataSource(){
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "dataSource")
    @Primary
    public AbstractRoutingDataSource dataSource(){
        MasterSlaveRoutingDataSource masterSlaveRoutingDataSource = new MasterSlaveRoutingDataSource();
        Map<Object, Object> targetDataResources = new HashMap<>();
//        targetDataResources.put(DbContextHolder.DbType.MASTER, dbTestDataSource());
//        targetDataResources.put(DbContextHolder.DbType.SLAVE, dbSlaveDataSource());
        targetDataResources.put(DbEnum.MASTER, dbTestDataSource());
        targetDataResources.put(DbEnum.SLAVE, dbSlaveDataSource());
        masterSlaveRoutingDataSource.setDefaultTargetDataSource(dbSlaveDataSource());
        masterSlaveRoutingDataSource.setTargetDataSources(targetDataResources);
        masterSlaveRoutingDataSource.afterPropertiesSet();
        return masterSlaveRoutingDataSource;
    }
}
