package com.yellow.pepper.context.accounts.accounts.domain;

import com.yellow.pepper.context.accounts.shared.domain.Identifier;
import lombok.EqualsAndHashCode;

import java.util.UUID;

/**
 * Class that represents the Account Transfer Id's value object
 */
@EqualsAndHashCode
public final class AccountTransferId extends Identifier {
  private static final long serialVersionUID = 5232120008643013436L;

  /**
   * Constructor.
   *
   * @param value the identifier
   */
  public AccountTransferId(String value) {
    super(value);
  }

  /**
   * Constructor
   *
   * @param value the identifier
   */
  public AccountTransferId(UUID value) {
    super(value);
  }
}
