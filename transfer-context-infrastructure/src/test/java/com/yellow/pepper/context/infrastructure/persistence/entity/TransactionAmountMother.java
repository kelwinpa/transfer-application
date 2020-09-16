package com.yellow.pepper.context.infrastructure.persistence.entity;

import com.yellow.pepper.context.accounts.shared.domain.TransactionAmount;

public final class TransactionAmountMother {

  public static TransactionAmount validTransactionAmount() {
    return new TransactionAmount(100d);
  }
}
