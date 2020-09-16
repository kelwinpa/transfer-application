package com.yellow.pepper.context.accounts.domain.create;

import com.yellow.pepper.context.accounts.accounts.domain.create.CreateAccountCommand;

public final class CreateAccountCommandMother {

  public static CreateAccountCommand invalidAccountNumber() {
    return CreateAccountCommand.builder()
        .newAccount("")
        .currency("USD")
        .initialBalance(0)
        .build();
  }

  public static CreateAccountCommand invalidAccountCurrency() {
    return CreateAccountCommand.builder()
        .newAccount("12345")
        .currency("COP")
        .initialBalance(0)
        .build();
  }

  public static CreateAccountCommand invalidAccountBalance() {
    return CreateAccountCommand.builder()
        .newAccount("12345")
        .currency("USD")
        .initialBalance(-100)
        .build();
  }

  public static CreateAccountCommand valid() {
    return CreateAccountCommand.builder()
        .newAccount("12345")
        .currency("USD")
        .build();
  }
}
