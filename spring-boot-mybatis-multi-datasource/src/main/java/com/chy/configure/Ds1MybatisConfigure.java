package com.chy.configure;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by chy on 2018/10/9.
 */
@Slf4j
@Configuration
@MapperScan(basePackages = {"com.chy.dao.ds1"}, sqlSessionTemplateRef = "ds1SqlSessionTemplate")
public class Ds1MybatisConfigure {

    @Bean(name = "ds1DataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.ds1")
    public DataSource ds1DataSource() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    @Bean(name = "ds1SqlSessionFactory")
    @Primary
    public SqlSessionFactory ds1SqlSessionFactory(@Qualifier("ds1DataSource") DataSource dataSource) {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //添加XML目录
        try {
            factoryBean.setMapperLocations(resolver.getResources("classpath:dao/ds1/*Mapper.xml"));
            return factoryBean.getObject();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }


    @Bean(name = "ds1TransactionManager")
    @Primary
    public DataSourceTransactionManager ds1TransactionManager(@Qualifier("ds1DataSource")DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "ds1SqlSessionTemplate")
    @Primary
    public SqlSessionTemplate ds1SqlSessionTemplate(@Qualifier("ds1SqlSessionFactory")SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}