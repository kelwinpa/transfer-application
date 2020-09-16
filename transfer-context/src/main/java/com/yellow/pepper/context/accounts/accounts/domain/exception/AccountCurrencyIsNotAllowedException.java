package com.yellow.pepper.context.accounts.accounts.domain.exception;

import com.yellow.pepper.context.accounts.shared.domain.exception.TransferException;

/**
 * Account Currency Is Not Allowed Exception Class
 */
public class AccountCurrencyIsNotAllowedException extends TransferException {

  /**
   * Constructor
   */
  public AccountCurrencyIsNotAllowedException(String currency) {
    super("AccountCurrencyIsNotAllowedException", String.format("The account currency <%s> is not allowed", currency),
        "1002");
  }
}
