package com.yellow.pepper.context.infrastructure.persistence.entity;

import com.yellow.pepper.context.accounts.accounts.domain.AccountNumber;

public final class AccountNumberMother {

  public static AccountNumber valid1AccountNumber() {
    return new AccountNumber("12345");
  }

  public static AccountNumber valid2AccountNumber() {
    return new AccountNumber("54321");
  }

  public static AccountNumber valid3AccountNumber() {
    return new AccountNumber("98765");
  }
}
