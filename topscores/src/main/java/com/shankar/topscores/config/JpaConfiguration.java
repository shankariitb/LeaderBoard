package com.shankar.topscores.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class JpaConfiguration {
    public static final String DATASOURCE_PERSISTENT_WRITE = "dsPersistentWrite";
    private static final String packagesToScan = "com.shankar.topscores.domain.jpa";

    @Bean(name = DATASOURCE_PERSISTENT_WRITE)
    @Primary
    @ConfigurationProperties(prefix = "spring.persistent.write")
    public DataSource persistentDataSource() {
        return dataSourceBuilder().build();
    }

    private DataSourceBuilder dataSourceBuilder() {
        return DataSourceBuilder.create().type(HikariDataSource.class);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
        factory.setJpaProperties(hibernateProperties);
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(packagesToScan);
        factory.setDataSource(persistentDataSource());
        return factory;
    }
}
