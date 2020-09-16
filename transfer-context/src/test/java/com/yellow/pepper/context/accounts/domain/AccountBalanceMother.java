package com.yellow.pepper.context.accounts.domain;

import com.yellow.pepper.context.accounts.accounts.domain.AccountBalance;

public final class AccountBalanceMother {

  public static AccountBalance oneHundredAccountBalance() {
    return new AccountBalance(100d);
  }

  public static AccountBalance twoHundredAccountBalance() {
    return new AccountBalance(200d);
  }

  public static AccountBalance threeHundredAccountBalance() {
    return new AccountBalance(300d);
  }
}
