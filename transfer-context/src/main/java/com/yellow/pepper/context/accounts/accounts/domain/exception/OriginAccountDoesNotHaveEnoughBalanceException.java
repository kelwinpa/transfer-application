package com.yellow.pepper.context.accounts.accounts.domain.exception;

import com.yellow.pepper.context.accounts.shared.domain.exception.TransferException;

/**
 * Origin Account Does Not Have Enough Balance
 */
public class OriginAccountDoesNotHaveEnoughBalanceException extends TransferException {

  /**
   * Constructor
   *
   * @param accountNumber The account number
   */
  public OriginAccountDoesNotHaveEnoughBalanceException(String accountNumber) {
    super("OriginAccountDoesNotHaveEnoughBalanceException",
        String.format("The account with number <%s> does not have enough balance", accountNumber), "1004");
  }
}
