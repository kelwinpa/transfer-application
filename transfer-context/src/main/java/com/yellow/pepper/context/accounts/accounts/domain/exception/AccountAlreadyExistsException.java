package com.yellow.pepper.context.accounts.accounts.domain.exception;

import com.yellow.pepper.context.accounts.shared.domain.exception.TransferException;

/**
 * Account Already Exists Exception
 */
public class AccountAlreadyExistsException extends TransferException {

  /**
   * Constructor
   *
   * @param accountNumber The account number
   */
  public AccountAlreadyExistsException(String accountNumber) {
    super("AccountAlreadyExistsException", String.format("The account with number <%s> already exists",
        accountNumber),
        "1010");
  }
}
