package com.yellow.pepper.context.accounts.accounts.domain.exception;

import com.yellow.pepper.context.accounts.shared.domain.exception.TransferException;

/**
 * Account Should Not Have Negative Balance Exception Class
 */
public class AccountShouldNotHaveNegativeBalanceException extends TransferException {

  /**
   * Constructor
   */
  public AccountShouldNotHaveNegativeBalanceException() {
    super("AccountShouldNotHaveNegativeBalanceException", "The account should not have negative balances", "1001");
  }
}
