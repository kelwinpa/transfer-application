package com.yellow.pepper.context.accounts.accounts.domain.create;

/**
 * Interface to create an acount
 */
public interface ICreateAccount {

  /**
   * Method responsible to create an account
   *
   * @param createAccountCommand The create account command
   * @return void
   */
  Void Execute(CreateAccountCommand createAccountCommand);
}
