package com.epam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import javax.sql.DataSource;


@Configuration
@ComponentScan(basePackages = "com.epam", excludeFilters = {@ComponentScan.Filter(Configuration.class),@ComponentScan.Filter(Controller.class)})
public class ApplicationConfiguration {

    @Inject
    protected Environment environment;

    @Autowired
    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.H2).addScripts("database/create_tables.sql", "database/update_tables.sql").build();
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

}
