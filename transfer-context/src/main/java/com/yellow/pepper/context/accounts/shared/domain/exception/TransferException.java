package com.yellow.pepper.context.accounts.shared.domain.exception;

import lombok.Getter;

/**
 * Global transfer exception
 */
@Getter
public class TransferException extends RuntimeException {
  private static final long serialVersionUID = 7489608652664610054L;

  private final TransferError error;

  /**
   * Constructor 1
   *
   * @param title       the title
   * @param description the description
   * @param code        the error code
   */
  public TransferException(String title, String description, String code) {
    this.error = new TransferError(title, description, code);
  }

  /**
   * Constructor 2
   *
   * @param title       the title
   * @param description the description
   * @param code        the error code
   * @param cause       the throwable cause
   */
//  public TransferException(String title, String description, String code, Throwable cause) {
//    super(cause);
//    this.error = new TransferError(title, description, code);
//  }
}
