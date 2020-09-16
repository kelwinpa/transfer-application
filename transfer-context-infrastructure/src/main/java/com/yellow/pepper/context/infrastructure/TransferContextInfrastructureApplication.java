package com.yellow.pepper.context.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.yellow.pepper.context.infrastructure.persistence.repository",
    "com.yellow.pepper.context.infrastructure.persistence.implementation"})
public class TransferContextInfrastructureApplication {

  public static void main(String[] args) {
    SpringApplication.run(TransferContextInfrastructureApplication.class, args);
  }
}
