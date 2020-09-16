package com.yellow.pepper.context.accounts.accounts.domain.exception;

import com.yellow.pepper.context.accounts.shared.domain.exception.TransferException;

/**
 * Transfer Limit Exceeded Exception
 */
public class TransferLimitExceededException extends TransferException {

  /**
   * Constructor
   */
  public TransferLimitExceededException() {
    super("TransferLimitExceededException", "The transfer limit has been exceeded", "1009");
  }
}
