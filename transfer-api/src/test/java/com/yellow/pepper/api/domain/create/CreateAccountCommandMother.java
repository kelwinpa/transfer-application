package com.yellow.pepper.api.domain.create;

import com.yellow.pepper.context.accounts.accounts.domain.create.CreateAccountCommand;

public final class CreateAccountCommandMother {

  public static CreateAccountCommand valid() {
    return CreateAccountCommand.builder()
        .newAccount("12345")
        .currency("USD")
        .initialBalance(0)
        .build();
  }

  public static CreateAccountCommand invalid() {
    return CreateAccountCommand.builder()
        .newAccount("")
        .currency("COP")
        .initialBalance(-1)
        .build();
  }
}
