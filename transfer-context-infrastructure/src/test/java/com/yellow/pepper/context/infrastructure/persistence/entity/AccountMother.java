package com.yellow.pepper.context.infrastructure.persistence.entity;

import com.yellow.pepper.context.accounts.accounts.domain.Account;

public final class AccountMother {

  public static Account valid1() {
    return Account.build(AccountNumberMother.valid1AccountNumber(),
        AccountCurrencyMother.validAccountCurrency(), AccountBalanceMother.threeHundredAccountBalance());
  }

  public static Account valid1WithOneLessHundred() {
    return Account.build(AccountNumberMother.valid1AccountNumber(),
        AccountCurrencyMother.validAccountCurrency(), AccountBalanceMother.twoHundredAccountBalance());
  }

  public static Account valid2() {
    return Account.load(AccountNumberMother.valid2AccountNumber(),
        AccountCurrencyMother.validAccountCurrency(), AccountBalanceMother.oneHundredAccountBalance(),
        AccountTransferMother.oneAccountTransfer());
  }

  public static Account valid2WithOneMoreHundred() {
    return Account.build(AccountNumberMother.valid2AccountNumber(),
        AccountCurrencyMother.validAccountCurrency(), AccountBalanceMother.twoHundredAccountBalance());
  }

  public static Account valid3() {
    return Account.build(AccountNumberMother.valid3AccountNumber(),
        AccountCurrencyMother.validAccountCurrency(), AccountBalanceMother.oneHundredAccountBalance());
  }
}
