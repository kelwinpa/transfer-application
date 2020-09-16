package com.yellow.pepper.context.infrastructure.persistence.implementation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Rates
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "CAD"
})
public class Rates {

  @JsonProperty("CAD")
  private Double cad;

  @JsonProperty("CAD")
  public Double getCad() {
    return this.cad;
  }

  @JsonProperty("CAD")
  public void setCad(Double cad) {
    this.cad = cad;
  }
}