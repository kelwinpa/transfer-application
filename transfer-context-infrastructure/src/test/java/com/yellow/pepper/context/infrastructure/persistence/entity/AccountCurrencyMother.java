package com.yellow.pepper.context.infrastructure.persistence.entity;

import com.yellow.pepper.context.accounts.accounts.domain.AccountCurrency;

public final class AccountCurrencyMother {

  public static AccountCurrency validAccountCurrency() {
    return new AccountCurrency("USD");
  }
}
