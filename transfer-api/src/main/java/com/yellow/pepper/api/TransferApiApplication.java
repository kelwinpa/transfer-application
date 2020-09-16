package com.yellow.pepper.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Transfer API application
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.yellow.pepper"})
public class TransferApiApplication {

  /**
   * Spring Application Run.
   *
   * @param args the list of args
   */
  public static void main(String[] args) {
    SpringApplication.run(TransferApiApplication.class, args);
  }
}
