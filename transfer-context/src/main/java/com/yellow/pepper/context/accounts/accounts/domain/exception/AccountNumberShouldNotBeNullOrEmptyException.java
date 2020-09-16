package com.yellow.pepper.context.accounts.accounts.domain.exception;

import com.yellow.pepper.context.accounts.shared.domain.exception.TransferException;

/**
 * Account Number Should Not Be Null Or Empty Exception Class
 */
public class AccountNumberShouldNotBeNullOrEmptyException extends TransferException {

  /**
   * Constructor
   */
  public AccountNumberShouldNotBeNullOrEmptyException() {
    super("AccountNumberShouldNotBeNullOrEmptyException", "The account number should not be null or empty", "1000");
  }
}
