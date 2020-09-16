package com.yellow.pepper.context.accounts.accounts.domain;

import com.yellow.pepper.context.accounts.accounts.domain.exception.AccountShouldNotHaveNegativeBalanceException;
import com.yellow.pepper.context.accounts.shared.domain.DoubleValueObject;

/**
 * Class to represent the value object of Account Balance
 */
public class AccountBalance extends DoubleValueObject {
  private static final long serialVersionUID = 6264422381051924847L;

  /**
   * Constructor
   *
   * @param value The value
   */
  public AccountBalance(Double value) {
    super(value);
    if (value < 0) {
      throw new AccountShouldNotHaveNegativeBalanceException();
    }
  }
}
