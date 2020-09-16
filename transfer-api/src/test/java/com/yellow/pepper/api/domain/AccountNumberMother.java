package com.yellow.pepper.api.domain;

import com.yellow.pepper.context.accounts.accounts.domain.AccountNumber;

public final class AccountNumberMother {

  public static AccountNumber validAccountNumber() {
    return new AccountNumber("12345");
  }

  public static AccountNumber anotherAccountNumber() {
    return new AccountNumber("54321");
  }
}
