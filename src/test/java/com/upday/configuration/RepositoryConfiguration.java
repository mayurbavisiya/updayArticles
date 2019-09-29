package com.upday.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.upday.domain"})
@EnableJpaRepositories(basePackages = {"com.upday.repositories"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
