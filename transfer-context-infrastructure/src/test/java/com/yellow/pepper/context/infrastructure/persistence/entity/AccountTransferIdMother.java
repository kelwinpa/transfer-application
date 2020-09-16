package com.yellow.pepper.context.infrastructure.persistence.entity;

import com.yellow.pepper.context.accounts.accounts.domain.AccountTransferId;

import java.util.UUID;

public final class AccountTransferIdMother {

  public static AccountTransferId validAccountTransferId() {
    return new AccountTransferId(UUID.randomUUID());
  }

}
