package com.yellow.pepper.context.accounts.accounts.domain;

import com.yellow.pepper.context.accounts.accounts.domain.exception.AccountNumberShouldNotBeNullOrEmptyException;
import com.yellow.pepper.context.accounts.shared.domain.StringValueObject;

/**
 * Class to represent the value object of an Account Number
 */
public class AccountNumber extends StringValueObject {

  private static final long serialVersionUID = -7803918735270297705L;

  /**
   * Constructor.
   *
   * @param value the value.
   */
  public AccountNumber(String value) {
    super(value);
    if (value == null || value.trim().isEmpty()) {
      throw new AccountNumberShouldNotBeNullOrEmptyException();
    }
  }
}
