package com.yellow.pepper.context.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Rest client config class
 */
@Configuration
public class RestClientConfig {

  /**
   * Rest template bean declaration
   *
   * @return Rest template
   */
  @Bean
  public RestTemplate getRestTemplate() {
    return new RestTemplate();
  }

}
