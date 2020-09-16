package com.yellow.pepper.context.accounts.shared.domain;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Generic value object for double.
 */
@EqualsAndHashCode
public abstract class DoubleValueObject implements Serializable {
  private static final long serialVersionUID = 9121920860838888897L;

  protected final Double value;

  /**
   * Constructor
   *
   * @param value The value
   */
  public DoubleValueObject(Double value) {
    this.value = value;
  }

  /**
   * Get the value
   *
   * @return The value
   */
  public Double value() {
    return value;
  }
}
