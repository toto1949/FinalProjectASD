package com.taoufiq.project.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.taoufiq.project.Repositories")
public class JpaConfig {
    // The configuration is handled through application.properties
    // This class ensures proper component scanning and transaction management
} 