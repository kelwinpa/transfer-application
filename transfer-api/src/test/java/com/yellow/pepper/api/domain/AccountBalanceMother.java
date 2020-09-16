package com.yellow.pepper.api.domain;

import com.yellow.pepper.context.accounts.accounts.domain.AccountBalance;

public final class AccountBalanceMother {

  public static AccountBalance twoHundredAccountBalance() {
    return new AccountBalance(200d);
  }
}
