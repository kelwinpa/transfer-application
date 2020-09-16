package com.yellow.pepper.context.infrastructure.persistence.entity;

import com.yellow.pepper.context.infrastructure.persistence.implementation.RateResponse;
import com.yellow.pepper.context.infrastructure.persistence.implementation.Rates;

public final class RateResponseMother {

  public static RateResponse valid() {
    var rateResponse = new RateResponse();

    var rates = new Rates();
    rates.setCad(1.3247);

    rateResponse.setRates(rates);

    return rateResponse;
  }
}
