package com.chy.configure;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Created by chy on 2018/10/9.
 */
@Slf4j
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="ds1EntityManagerFactory",
        transactionManagerRef="ds1TransactionManager",
        basePackages= { "com.chy.dao.ds1" }) //设置Repository所在位置
public class Ds1MybatisConfigure {

    @Resource
    private JpaProperties jpaProperties;

    @Autowired(required = false)
    private PersistenceUnitManager persistenceUnitManager;

    @Bean(name = "ds1DataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.ds1")
    public DataSource ds1DataSource() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    @Bean(name = "ds1EntityManagerFactoryBuilder")
    public EntityManagerFactoryBuilder ds1EntityManagerFactoryBuilder(){
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        return new EntityManagerFactoryBuilder(adapter, jpaProperties.getProperties(), persistenceUnitManager);
    }

    /**
     * 我们通过LocalContainerEntityManagerFactoryBean来获取EntityManagerFactory实例
     * @return
     */
    @Bean(name = "ds1EntityManagerFactoryBean")
    //@Primary
    public LocalContainerEntityManagerFactoryBean ds1EntityManagerFactoryBean(EntityManagerFactoryBuilder ds1EntityManagerFactoryBuilder) {

        DataSource dataSource = ds1DataSource();
        return ds1EntityManagerFactoryBuilder
                .dataSource(dataSource)
                .packages("com.chy.domain") //设置实体类所在位置
                .persistenceUnit("ds1PersistenceUnit")
                .build();
    }

    /**
     * EntityManagerFactory类似于Hibernate的SessionFactory,mybatis的SqlSessionFactory
     * 总之,在执行操作之前,我们总要获取一个EntityManager,这就类似于Hibernate的Session,
     * mybatis的sqlSession.
     * @param ds1EntityManagerFactoryBuilder
     * @return
     */
    @Bean(name = "ds1EntityManagerFactory")
    @Primary
    public EntityManagerFactory ds1EntityManagerFactory(EntityManagerFactoryBuilder ds1EntityManagerFactoryBuilder) {
        return this.ds1EntityManagerFactoryBean(ds1EntityManagerFactoryBuilder).getObject();
    }

    /**
     * 配置事物管理器
     * @return
     */
    @Bean(name = "ds1TransactionManager")
    @Primary
    public PlatformTransactionManager writeTransactionManager(EntityManagerFactoryBuilder ds1EntityManagerFactoryBuilder) {
        return new JpaTransactionManager(ds1EntityManagerFactory(ds1EntityManagerFactoryBuilder));
    }

}