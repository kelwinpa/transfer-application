package com.yellow.pepper.context.accounts.domain;

import com.yellow.pepper.context.accounts.shared.domain.TransactionDateTime;

import java.time.LocalDateTime;

public final class TransactionDateTimeMother {

  public static TransactionDateTime validTransactionDateTime() {
    return new TransactionDateTime(LocalDateTime.now());
  }
}
