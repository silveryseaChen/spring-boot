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
        entityManagerFactoryRef="ds2EntityManagerFactory",
        transactionManagerRef="ds2TransactionManager",
        basePackages= { "com.chy.dao.ds2" }) //设置Repository所在位置
public class Ds2MybatisConfigure {

    @Resource
    private JpaProperties jpaProperties;

    @Autowired(required = false)
    private PersistenceUnitManager persistenceUnitManager;

    @Bean(name = "ds2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.ds2")
    public DataSource ds2DataSource() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    @Bean(name = "ds2EntityManagerFactoryBuilder")
    public EntityManagerFactoryBuilder ds2EntityManagerFactoryBuilder(){
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        return new EntityManagerFactoryBuilder(adapter, jpaProperties.getProperties(), persistenceUnitManager);
    }

    /**
     * 我们通过LocalContainerEntityManagerFactoryBean来获取EntityManagerFactory实例
     * @return
     */
    @Bean(name = "ds2EntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean ds2EntityManagerFactoryBean(EntityManagerFactoryBuilder ds2EntityManagerFactoryBuilder) {

        DataSource dataSource = ds2DataSource();
        return ds2EntityManagerFactoryBuilder
                .dataSource(dataSource)
                .packages("com.chy.domain") //设置实体类所在位置
                .persistenceUnit("ds2PersistenceUnit")
                .build();
    }

    /**
     * EntityManagerFactory类似于Hibernate的SessionFactory,mybatis的SqlSessionFactory
     * 总之,在执行操作之前,我们总要获取一个EntityManager,这就类似于Hibernate的Session,
     * mybatis的sqlSession.
     * @param ds2EntityManagerFactoryBuilder
     * @return
     */
    @Bean(name = "ds2EntityManagerFactory")
    public EntityManagerFactory ds2EntityManagerFactory(EntityManagerFactoryBuilder ds2EntityManagerFactoryBuilder) {
        return this.ds2EntityManagerFactoryBean(ds2EntityManagerFactoryBuilder).getObject();
    }

    /**
     * 配置事物管理器
     * @return
     */
    @Bean(name = "ds2TransactionManager")
    public PlatformTransactionManager writeTransactionManager(EntityManagerFactoryBuilder ds2EntityManagerFactoryBuilder) {
        return new JpaTransactionManager(ds2EntityManagerFactory(ds2EntityManagerFactoryBuilder));
    }

}