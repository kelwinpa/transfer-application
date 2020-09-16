package com.yellow.pepper.context.accounts.shared.domain.exception;

/**
 * Identifier Malformed Exception
 */
public class IdentifierMalformedException extends TransferException {

  /**
   * Constructor.
   *
   * @param identifier the identifier
   */
  public IdentifierMalformedException(String identifier) {
    super("IdentifierMalformedException", String.format("The identifier <%s> is malformed", identifier), "1006");
  }
}
