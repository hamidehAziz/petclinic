package org.springframework.samples.petclinic.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
    entityManagerFactoryRef = "h2EntityManagerFactory",
    transactionManagerRef = "h2TransactionManager",
    basePackages = {
    "org.springframework.samples.petclinic.mysql.repo"
})
@EnableTransactionManagement
public class H2TestProfileJpaConfig {

    @Bean( name = "h2DataSource")
    @Profile("test")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
        dataSource.setUrl("jdbc:hsqldb:mem:testdb;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("sa");

        return dataSource;
    }

    @Bean(name = "h2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    postgresEntityManagerFactory(
        EntityManagerFactoryBuilder builder,
        @Qualifier("h2DataSource") DataSource dataSource
    ) {
        return
            builder
                .dataSource(dataSource)
                .packages("org.springframework.samples.petclinic.postgres.domain")
                .persistenceUnit("postgres")
                .build();
    }
    @Bean(name = "h2TransactionManager")
    public PlatformTransactionManager postgresTransactionManager(
        @Qualifier("h2EntityManagerFactory") EntityManagerFactory
            postgresEntityManagerFactory
    ) {
        return new JpaTransactionManager(postgresEntityManagerFactory);
    }
    // configure entityManagerFactory
    // configure transactionManager
    // configure additional Hibernate properties
}
