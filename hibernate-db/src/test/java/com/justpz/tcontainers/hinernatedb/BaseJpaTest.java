package com.justpz.tcontainers.hinernatedb;

import ch.qos.logback.classic.LoggerContext;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.invoke.MethodHandles;

@SpringBootTest
@SpringJUnitConfig(value = BaseJpaTest.Config.class, initializers = {BaseJpaTest.Initializer.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
public abstract class BaseJpaTest {

    protected static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    static {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.setMaxCallerDataDepth(30);
    }

    @Container
    public static PostgreSQLContainer postgreSQLContainer;

    static {
        postgreSQLContainer = new PostgreSQLContainer()
                .withDatabaseName("integration-tests-db")
                .withUsername("sa")
                .withPassword("sa");
    }

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @PersistenceContext
    protected EntityManager em;
    @Autowired
    public TransactionTemplate transactionTemplate;


    protected void doInTransaction(final Runnable runnable) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                runnable.run();
            }
        });
    }

    @Configuration
    @ComponentScan("com.justpz.tcontainers.hinernatedb")
    public static class Config {
    }
}
