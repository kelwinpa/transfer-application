package com.yellow.pepper.context.accounts.domain;

import com.yellow.pepper.context.accounts.accounts.domain.Account;

import java.util.Collections;
import java.util.Optional;

public final class AccountMother {

  public static Optional<Account> accountWithOneHundredAndThreeTransfers() {
    return Optional
        .of(Account.load(AccountNumberMother.validAccountNumber(), AccountCurrencyMother.validAccountCurrency(),
            AccountBalanceMother.oneHundredAccountBalance(), AccountTransferMother.threeCurrentAccountTransfers()));
  }

  public static Optional<Account> accountWithOneHundredAndOneTransfer() {
    return Optional
        .of(Account.load(AccountNumberMother.validAccountNumber(), AccountCurrencyMother.validAccountCurrency(),
            AccountBalanceMother.oneHundredAccountBalance(), AccountTransferMother.oneAccountTransfer()));
  }

  public static Optional<Account> anotherAccountWithOneHundredAndOneTransfer() {
    return Optional
        .of(Account.load(AccountNumberMother.anotherAccountNumber(), AccountCurrencyMother.validAccountCurrency(),
            AccountBalanceMother.oneHundredAccountBalance(), AccountTransferMother.oneAccountTransfer()));
  }

  public static Optional<Account> accountWithTwoHundred() {
    return Optional
        .of(Account.load(AccountNumberMother.validAccountNumber(), AccountCurrencyMother.validAccountCurrency(),
            AccountBalanceMother.twoHundredAccountBalance(), Collections.emptySet()));
  }

  public static Optional<Account> accountWithThreeHundred() {
    return Optional
        .of(Account.load(AccountNumberMother.validAccountNumber(), AccountCurrencyMother.validAccountCurrency(),
            AccountBalanceMother.threeHundredAccountBalance(), null));
  }
}
