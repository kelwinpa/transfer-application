package com.yellow.pepper.api.domain;

import com.yellow.pepper.context.accounts.accounts.domain.AccountCurrency;

public final class AccountCurrencyMother {

  public static AccountCurrency validAccountCurrency() {
    return new AccountCurrency("USD");
  }
}
