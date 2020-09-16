package com.yellow.pepper.context.accounts.accounts.domain;

import com.yellow.pepper.context.accounts.shared.domain.TransactionAmount;
import com.yellow.pepper.context.accounts.shared.domain.TransactionCurrency;
import com.yellow.pepper.context.accounts.shared.domain.TransactionDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Account transfer
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class AccountTransfer {

  private final AccountTransferId accountTransferId;
  private final AccountNumber destinationAccountNumber;
  private final TransactionAmount transactionAmount;
  private final TransactionDateTime transactionDateTime;
  private final TransactionCurrency transactionCurrency;
  private final String description;

  /**
   * Builder
   *
   * @param destinationAccountNumber The destination account number
   * @param transactionAmount        The transaction amount
   * @param transactionCurrency      The transaction currency
   * @param description              A description
   * @return An account transfer object
   */
  public static AccountTransfer build(AccountNumber destinationAccountNumber, TransactionAmount transactionAmount,
                                      TransactionCurrency transactionCurrency, String description) {
    return new AccountTransfer(new AccountTransferId(UUID.randomUUID()), destinationAccountNumber, transactionAmount,
        new TransactionDateTime(LocalDateTime.now()), transactionCurrency, description);
  }

  /**
   * Load
   *
   * @param accountTransferId        The account transfer id
   * @param destinationAccountNumber The destination account number
   * @param transactionAmount        The transaction amount
   * @param transactionDateTime      The transaction date time
   * @param transactionCurrency      The transaction currency
   * @param description              A description
   * @return An account transfer object
   */
  public static AccountTransfer load(AccountTransferId accountTransferId, AccountNumber destinationAccountNumber,
                                     TransactionAmount transactionAmount, TransactionDateTime transactionDateTime,
                                     TransactionCurrency transactionCurrency, String description) {
    return new AccountTransfer(accountTransferId, destinationAccountNumber, transactionAmount, transactionDateTime,
        transactionCurrency,
        description);
  }
}
