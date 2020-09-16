package com.yellow.pepper.context.accounts.accounts.application.find;

import com.yellow.pepper.context.accounts.accounts.domain.Account;
import com.yellow.pepper.context.accounts.accounts.domain.AccountNumber;
import com.yellow.pepper.context.accounts.accounts.domain.IAccountRepository;
import com.yellow.pepper.context.accounts.accounts.domain.exception.AccountDoesNotExistException;
import com.yellow.pepper.context.accounts.accounts.domain.find.FindAccountQuery;
import com.yellow.pepper.context.accounts.accounts.domain.find.IFindAccount;

/**
 * Find account use case
 */
public class FindAccount implements IFindAccount {

  private final IAccountRepository accountRepository;

  /**
   * Constructor
   *
   * @param accountRepository The account repository
   */
  public FindAccount(IAccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public Account Execute(FindAccountQuery findAccountQuery) {

    var accountNumber = new AccountNumber(findAccountQuery.getAccountNumber());
    var optionalAccount = accountRepository.findByAccountNumber(accountNumber);

    if (optionalAccount.isEmpty()) {
      throw new AccountDoesNotExistException(accountNumber.value());
    }

    return optionalAccount.get();
  }
}
