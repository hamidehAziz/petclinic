package org.springframework.samples.petclinic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "postgresEntityManagerFactory",
    transactionManagerRef = "postgresTransactionManager",
    basePackages = {"org.springframework.samples.petclinic.postgres.repo"}
)
public class PostgresDBConfig {

    @Autowired
    Environment env;

    @Bean(name = "postgresDataSource")
    @ConfigurationProperties(prefix = "postgres.datasource")
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(env.getProperty("postgres.datasource.url"));
        dataSourceBuilder.driverClassName(env.getProperty("postgres.datasource.driver-class-name"));
        dataSourceBuilder.username(env.getProperty("postgres.datasource.username"));
        dataSourceBuilder.password(env.getProperty("postgres.datasource.password"));
        return dataSourceBuilder.build();
    }

    @Bean(name = "postgresEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    postgresEntityManagerFactory(
        EntityManagerFactoryBuilder builder,
        @Qualifier("postgresDataSource") DataSource dataSource
    ) {
        return
            builder
                .dataSource(dataSource)
                .packages("org.springframework.samples.petclinic.postgres.domain")
                .persistenceUnit("postgres")
                .build();
    }
    @Bean(name = "postgresTransactionManager")
    public PlatformTransactionManager postgresTransactionManager(
        @Qualifier("postgresEntityManagerFactory") EntityManagerFactory
            postgresEntityManagerFactory
    ) {
        return new JpaTransactionManager(postgresEntityManagerFactory);
    }
}
