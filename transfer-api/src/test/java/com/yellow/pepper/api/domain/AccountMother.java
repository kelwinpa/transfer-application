package com.yellow.pepper.api.domain;

import com.yellow.pepper.context.accounts.accounts.domain.Account;

import java.util.Optional;

public final class AccountMother {

  public static Optional<Account> accountWithTwoHundred() {
    return Optional
        .of(Account.load(AccountNumberMother.validAccountNumber(), AccountCurrencyMother.validAccountCurrency(),
            AccountBalanceMother.twoHundredAccountBalance(), AccountTransferMother.oneAccountTransfer()));
  }
}
