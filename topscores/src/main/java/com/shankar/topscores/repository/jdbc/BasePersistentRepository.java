package com.shankar.topscores.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public abstract class BasePersistentRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getWriteAccess() {
        return jdbcTemplate;
    }
}
