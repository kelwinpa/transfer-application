package com.yellow.pepper.context.accounts.accounts.application.create;

import com.yellow.pepper.context.accounts.accounts.domain.Account;
import com.yellow.pepper.context.accounts.accounts.domain.AccountBalance;
import com.yellow.pepper.context.accounts.accounts.domain.AccountCurrency;
import com.yellow.pepper.context.accounts.accounts.domain.AccountNumber;
import com.yellow.pepper.context.accounts.accounts.domain.IAccountRepository;
import com.yellow.pepper.context.accounts.accounts.domain.create.CreateAccountCommand;
import com.yellow.pepper.context.accounts.accounts.domain.create.ICreateAccount;
import com.yellow.pepper.context.accounts.accounts.domain.exception.AccountAlreadyExistsException;

/**
 * Create account use case
 */
public class CreateAccount implements ICreateAccount {

  private final IAccountRepository accountRepository;

  /**
   * Constructor
   *
   * @param accountRepository The account repository
   */
  public CreateAccount(IAccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public Void Execute(CreateAccountCommand createAccountCommand) {

    var accountNumber = new AccountNumber(createAccountCommand.getNewAccount());
    var accountCurrency = new AccountCurrency(createAccountCommand.getCurrency());
    var accountBalance = new AccountBalance(createAccountCommand.getInitialBalance());

    var optionalAccount = accountRepository.findByAccountNumber(accountNumber);

    if (optionalAccount.isPresent()) {
      throw new AccountAlreadyExistsException(accountNumber.value());
    }

    var account = Account.build(accountNumber, accountCurrency, accountBalance);
    accountRepository.saveAccount(account);
    return null;
  }
}
