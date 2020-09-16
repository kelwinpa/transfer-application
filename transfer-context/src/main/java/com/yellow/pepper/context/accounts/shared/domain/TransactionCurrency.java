package com.yellow.pepper.context.accounts.shared.domain;

import com.yellow.pepper.context.accounts.shared.domain.exception.TransactionCurrencyIsNotAllowedException;

import java.util.Arrays;

/**
 * Class to represent the value object of an Transaction Currency
 */
public class TransactionCurrency extends StringValueObject {
  private static final long serialVersionUID = -7505430288142254161L;

  private static final String[] CURRENCIES = new String[]{"USD"};

  /**
   * Constructor.
   *
   * @param value the value.
   */
  public TransactionCurrency(String value) {
    super(value);
    if (Arrays.stream(CURRENCIES).noneMatch(value::equalsIgnoreCase)) {
      throw new TransactionCurrencyIsNotAllowedException(value);
    }
  }
}
