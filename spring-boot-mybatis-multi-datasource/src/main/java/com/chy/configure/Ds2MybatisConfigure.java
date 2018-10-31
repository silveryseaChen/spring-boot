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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by chy on 2018/10/9.
 */
@Configuration
@Slf4j
@MapperScan(basePackages = {"com.chy.dao.ds2"}, sqlSessionTemplateRef = "ds2SqlSessionTemplate")
public class Ds2MybatisConfigure {

    @Bean(name = "ds2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.ds2")
    public DataSource ds2DataSource() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    @Bean(name = "ds2SqlSessionFactory")
    public SqlSessionFactory ds2SqlSessionFactoryBean(@Qualifier("ds2DataSource") DataSource dataSource){
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //添加XML目录
        try {
            factoryBean.setMapperLocations(resolver.getResources("classpath:dao/ds2/*.xml"));
            return factoryBean.getObject();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new RuntimeException(e);
        }
    }

    @Bean(name = "ds2TransactionManager")
    public DataSourceTransactionManager ds1TransactionManager(@Qualifier("ds2DataSource")DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "ds2SqlSessionTemplate")
    public SqlSessionTemplate ds2SqlSessionTemplate(@Qualifier("ds2SqlSessionFactory")SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
