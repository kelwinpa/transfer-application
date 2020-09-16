package com.yellow.pepper.context.accounts.accounts.domain.exception;

import com.yellow.pepper.context.accounts.shared.domain.exception.TransferException;

/**
 * Account Does Not Exist Exception
 */
public class AccountDoesNotExistException extends TransferException {

  /**
   * Constructor
   *
   * @param accountNumber The account number
   */
  public AccountDoesNotExistException(String accountNumber) {
    super("AccountDoesNotExistException", String.format("The account with number <%s> does not exist", accountNumber),
        "1003");
  }
}
