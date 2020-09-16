package com.yellow.pepper.context.accounts.shared.domain;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Generic value object type string.
 */
@EqualsAndHashCode
public abstract class StringValueObject implements Serializable {
  protected final String value;

  /**
   * Constructor.
   *
   * @param value the value.
   */
  public StringValueObject(String value) {
    this.value = value;
  }

  /**
   * Get the value.
   *
   * @return the value.
   */
  public String value() {
    return value;
  }

}
