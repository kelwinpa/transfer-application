package com.yellow.pepper.context.accounts.shared.domain.exception;

/**
 * Date Time Malformed Exception
 */
public class DateTimeMalformedException extends TransferException {

  /**
   * Constructor.
   *
   * @param datetime the date time
   */
  public DateTimeMalformedException(String datetime) {
    super("DateTimeMalformedException",
        String.format("The date time <%s> is malformed in format ISO 8601", datetime), "1007");
  }
}
