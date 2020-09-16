package com.yellow.pepper.context.accounts.shared.domain;

import com.yellow.pepper.context.accounts.shared.domain.exception.IdentifierMalformedException;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.NonFinal;

import java.io.Serializable;
import java.util.UUID;

/**
 * Generic value object of type identifier.
 */
@Value
@NonFinal
@EqualsAndHashCode
public abstract class Identifier implements Serializable {

  protected String value;

  /**
   * Constructor 1
   *
   * @param value the identifier
   */
  public Identifier(String value) {
    ensureValidUuid(value);
    this.value = value;
  }

  /**
   * Constructor 2
   *
   * @param value The identifier
   */
  public Identifier(UUID value) {
    this.value = value.toString();
  }

  /**
   * Validate the UUID format.
   *
   * @param value the identifier.
   */
  private static void ensureValidUuid(String value) {
    try {
      UUID.fromString(value);
    } catch (IllegalArgumentException ex) {
      var exception = new IdentifierMalformedException(value);
      exception.initCause(ex);
      throw exception;
    }
  }

  /**
   * Get identifier.
   *
   * @return the identifier.
   */
  public String value() {
    return value;
  }

  @Override
  public String toString() {
    if (null == value) {
      return "";
    }
    return value;
  }
}