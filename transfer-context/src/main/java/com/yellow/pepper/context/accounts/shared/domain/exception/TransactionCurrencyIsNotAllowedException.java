package com.yellow.pepper.context.accounts.shared.domain.exception;

/**
 * Transaction Currency Is Not Allowed Exception Class
 */
public class TransactionCurrencyIsNotAllowedException extends TransferException {

  /**
   * Constructor
   */
  public TransactionCurrencyIsNotAllowedException(String currency) {
    super("TransactionCurrencyIsNotAllowedException", String.format("The transaction currency <%s> is not allowed",
        currency), "1008");
  }
}
