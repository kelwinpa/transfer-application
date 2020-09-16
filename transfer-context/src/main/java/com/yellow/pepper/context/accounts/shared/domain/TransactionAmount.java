package com.yellow.pepper.context.accounts.shared.domain;

import com.yellow.pepper.context.accounts.shared.domain.exception.TransferAmountMustBeGreaterThanZeroException;

/**
 * Class to represent the value object of Transaction Amount
 */
public class TransactionAmount extends DoubleValueObject {
  private static final long serialVersionUID = -7245296315662910827L;

  /**
   * Constructor
   *
   * @param value The value
   */
  public TransactionAmount(Double value) {
    super(value);
    if (value <= 0) {
      throw new TransferAmountMustBeGreaterThanZeroException();
    }
  }
}
