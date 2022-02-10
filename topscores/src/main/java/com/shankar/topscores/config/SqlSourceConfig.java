package com.shankar.topscores.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class SqlSourceConfig {
    public static final String DATASOURCE_SCORE_WRITE ="dsScoreWrite";
    public static final String JDBCTEMPLATE_SCORE_READ = "jdbcScoreWrite";

    @Value("${spring.write.datasource.type:#{null}}")
    private Class<? extends DataSource> type;

    private DataSourceBuilder dataSourceBuilder() {
        if (type != null) {
            return DataSourceBuilder.create().type(type);
        }
        return DataSourceBuilder.create();
    }

    @Bean(name = DATASOURCE_SCORE_WRITE)
    @Primary
    @ConfigurationProperties(prefix = "spring.persistent.write")
    public DataSource musicReadDataSource() {
        return dataSourceBuilder().build();
    }

    @Bean(name = JDBCTEMPLATE_SCORE_READ)
    @Autowired
    @Qualifier(DATASOURCE_SCORE_WRITE)
    public JdbcTemplate musicReadJdbcTemplate(@Qualifier(DATASOURCE_SCORE_WRITE) DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }
}
