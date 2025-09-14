package org.lucentrix.demo.ollama.config;


import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "org.lucentrix.demo.ollama.repository",
        entityManagerFactoryRef = "ollamaEntityManagerFactory",
        transactionManagerRef = "ollamaTransactionManager"
)
public class OllamaJpaConfig {

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean ollamaEntityManagerFactory(
            EntityManagerFactoryBuilder builder, // This will now be available
            @Qualifier("ollamaDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("org.lucentrix.demo.ollama.model")
                .persistenceUnit("ollama")
                .properties(jpaProperties())
                .build();
    }


    @Primary
    @Bean
    public PlatformTransactionManager ollamaTransactionManager(
            @Qualifier("ollamaEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    private Map<String, Object> jpaProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        props.put("hibernate.hbm2ddl.auto", "update");
        props.put("hibernate.show_sql", true);
        props.put("hibernate.format_sql", true);
        return props;
    }
}
