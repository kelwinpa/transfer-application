package com.yellow.pepper.context.accounts.shared.domain.exception;

/**
 * Transfer Amount Must Be Greater Than Zero Exception
 */
public class TransferAmountMustBeGreaterThanZeroException extends TransferException {

  /**
   * Constructor
   */
  public TransferAmountMustBeGreaterThanZeroException() {
    super("TransferAmountMustBeGreaterThanZeroException", "The transfer amount must be greater than zero", "1005");
  }
}
