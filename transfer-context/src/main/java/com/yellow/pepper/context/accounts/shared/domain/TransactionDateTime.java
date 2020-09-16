package com.yellow.pepper.context.accounts.shared.domain;

import java.time.LocalDateTime;

/**
 * Class to represent the value object of Transaction Date Time
 */
public class TransactionDateTime extends DateTimeValueObject {
  private static final long serialVersionUID = 8126852210349330028L;

  /**
   * Constructor 2
   *
   * @param dateTime The date time
   */
  public TransactionDateTime(String dateTime) {
    super(dateTime);
  }

  /**
   * Constructor 2
   *
   * @param dateTime
   */
  public TransactionDateTime(LocalDateTime dateTime) {
    super(dateTime);
  }

}
