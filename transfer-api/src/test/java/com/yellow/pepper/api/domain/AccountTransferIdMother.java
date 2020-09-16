package com.yellow.pepper.api.domain;

import com.yellow.pepper.context.accounts.accounts.domain.AccountTransferId;

import java.util.UUID;

public final class AccountTransferIdMother {

  public static AccountTransferId validAccountTransferId() {
    return new AccountTransferId(UUID.randomUUID());
  }

  public static AccountTransferId valid2AccountTransferId() {
    return new AccountTransferId("3bee3741-f4e4-4f8d-85af-4e6bc46c0270");
  }
}
