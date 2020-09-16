package com.yellow.pepper.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger configuration
 */
@Configuration
public class SwaggerConfiguration {

  /**
   * Api info.
   *
   * @return ApiInfo
   */
  private static ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Transfer API")
        .description("This is Transfer API")
        .license("Apache 2.0")
        .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
        .termsOfServiceUrl("")
        .version("1.0.0")
        .build();
  }

  /**
   * Custom docket implementation
   *
   * @return docket
   */
  @Bean
  public Docket customImplementation() {
    return new Docket(DocumentationType.OAS_30)
        .tags(new Tag("accounts", "accounts related"))
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.yellow.pepper.api"))
        .build()
        .apiInfo(apiInfo());
  }
}
