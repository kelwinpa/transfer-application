package com.yellow.pepper.context.infrastructure.persistence.entity;

import com.yellow.pepper.context.accounts.shared.domain.TransactionDateTime;

public final class TransactionDateTimeMother {

  public static TransactionDateTime validTransactionDateTime() {
    return new TransactionDateTime("2020-01-01 10:10:00");
  }
}
