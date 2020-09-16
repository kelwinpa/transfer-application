package com.yellow.pepper.context.accounts.accounts.domain.find;

import com.yellow.pepper.context.accounts.accounts.domain.Account;

/**
 * Interface to find an account
 */
public interface IFindAccount {

  Account Execute(FindAccountQuery findAccountQuery);
}
