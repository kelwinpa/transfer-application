package com.yellow.pepper.context.infrastructure.persistence.implementation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Rate response
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "rates"
})
public class RateResponse {

  @JsonProperty("rates")
  private Rates rates;

  @JsonProperty("rates")
  public Rates getRates() {
    return rates;
  }

  @JsonProperty("rates")
  public void setRates(Rates rates) {
    this.rates = rates;
  }
}
