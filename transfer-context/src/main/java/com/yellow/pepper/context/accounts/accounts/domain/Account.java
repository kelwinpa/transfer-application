package com.yellow.pepper.context.accounts.accounts.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Class represent the aggregate root of Account
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public final class Account {

  private static final int MAX_TRANSFERS = 3;

  private final AccountNumber accountNumber;
  private final AccountCurrency accountCurrency;
  private AccountBalance accountBalance;
  private Set<AccountTransfer> accountTransfers;

  /**
   * Builder
   *
   * @param accountNumber  The account number
   * @param accountBalance The account balance
   * @param accountBalance The account balance
   * @return An account object
   */
  public static Account build(AccountNumber accountNumber, AccountCurrency accountCurrency,
                              AccountBalance accountBalance) {
    return new Account(accountNumber, accountCurrency, accountBalance, new HashSet<>());
  }

  /**
   * Load an account
   *
   * @param accountNumber    The account number
   * @param accountBalance   The account balance
   * @param accountBalance   The account balance
   * @param accountTransfers The account transfers
   * @return An account object
   */
  public static Account load(AccountNumber accountNumber, AccountCurrency accountCurrency,
                             AccountBalance accountBalance, Set<AccountTransfer> accountTransfers) {

    if (accountTransfers == null || accountTransfers.isEmpty()) {
      accountTransfers = new HashSet<>();
    }
    return new Account(accountNumber, accountCurrency, accountBalance, accountTransfers);
  }

  /**
   * Method to validate whether an account has enough balance
   *
   * @return a boolean
   */
  public boolean hasEnoughBalance(double transferAmount) {
    return this.accountBalance.value() >= transferAmount;
  }

  /**
   * Enough transfer per day method
   *
   * @return a boolean
   */
  public boolean enoughTransfersPerDay() {
    return this.accountTransfers.stream()
        .filter(accountTransfer -> accountTransfer.getTransactionDateTime().value().toLocalDate().isEqual(
            LocalDate.now()))
        .count() >= MAX_TRANSFERS;
  }


  /**
   * Prepare origin account for new transfer method
   *
   * @param accountTransfer The account transfer
   */
  public void prepareOriginAccountForNewTransfer(AccountTransfer accountTransfer,
                                                 double totalTransferAmount) {
    this.accountBalance =
        new AccountBalance(this.accountBalance.value() - totalTransferAmount);
    this.accountTransfers.add(accountTransfer);
  }

  /**
   * Prepare destination account for new transfer method
   *
   * @param accountTransfer The account transfer
   */
  public void prepareDestinationAccountForNewTransfer(AccountTransfer accountTransfer) {
    this.accountBalance =
        new AccountBalance(this.accountBalance.value() + accountTransfer.getTransactionAmount().value());
  }
}
