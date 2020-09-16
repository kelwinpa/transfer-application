package com.yellow.pepper.context.accounts.shared.domain;

import com.yellow.pepper.context.accounts.shared.domain.exception.DateTimeMalformedException;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Generic value object of type Datetime.
 */
@EqualsAndHashCode
public abstract class DateTimeValueObject implements Serializable {
  private static final long serialVersionUID = 2642909305970704012L;

  private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-DD HH:mm:ss");
  protected final LocalDateTime value;

  /**
   * Constructor 1
   *
   * @param dateTime The date time
   */
  public DateTimeValueObject(String dateTime) {
    if (dateTime == null || dateTime.trim().isEmpty()) {
      this.value = null;
    } else {
      this.value = parseToLocalDateTime(dateTime);
    }
  }

  /**
   * Constructor 2
   *
   * @param dateTime The date time
   */
  public DateTimeValueObject(LocalDateTime dateTime) {
    this.value = dateTime;
  }

  /**
   * Validate the format of Date in ISO 8601.
   *
   * @param dateTime the date time.
   * @return date in LocalDateTime.
   */
  public static LocalDateTime parseToLocalDateTime(String dateTime) {
    try {
      return LocalDateTime.parse(dateTime, DATE_TIME_FORMAT);
    } catch (DateTimeParseException ex) {
      var exception = new DateTimeMalformedException(dateTime);
      exception.initCause(ex);
      throw exception;
    }
  }

  /**
   * Get value of Date time
   *
   * @return A Local date time
   */
  public LocalDateTime value() {
    return this.value;
  }

  @Override
  public String toString() {
    if (null == value) {
      return "";
    }
    return value.format(DATE_TIME_FORMAT);
  }
}
