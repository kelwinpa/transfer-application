package com.yellow.pepper.context.accounts.accounts.domain;

import com.yellow.pepper.context.accounts.accounts.domain.exception.AccountCurrencyIsNotAllowedException;
import com.yellow.pepper.context.accounts.shared.domain.StringValueObject;

import java.util.Arrays;

/**
 * Class to represent the value object of an Account Currency
 */
public class AccountCurrency extends StringValueObject {
  private static final long serialVersionUID = -6453948818555461280L;

  private static final String[] CURRENCIES = new String[]{"USD"};

  /**
   * Constructor.
   *
   * @param value the value.
   */
  public AccountCurrency(String value) {
    super(value);
    if (Arrays.stream(CURRENCIES).noneMatch(value::equalsIgnoreCase)) {
      throw new AccountCurrencyIsNotAllowedException(value);
    }
  }
}
