package com.yellow.pepper.context.accounts.accounts.domain;

import java.util.Optional;

/**
 * Account repository interface
 */
public interface IAccountRepository {

  /**
   * Find by account number
   *
   * @param accountNumber The account number
   * @return Optional of an account
   */
  Optional<Account> findByAccountNumber(AccountNumber accountNumber);

  /**
   * Transfer money
   *
   * @param originAccount      The origin account
   * @param destinationAccount The destination account
   */
  void transferMoney(Account originAccount, Account destinationAccount);

  /**
   * Find CAD exchange rate
   *
   * @param base The base
   * @return A value
   */
  double findCadExchangeRate(String base);

  /**
   * Save account
   *
   * @param account The account
   */
  void saveAccount(Account account);
}
