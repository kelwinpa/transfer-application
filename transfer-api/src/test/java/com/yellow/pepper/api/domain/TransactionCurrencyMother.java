package com.yellow.pepper.api.domain;

import com.yellow.pepper.context.accounts.shared.domain.TransactionCurrency;

public final class TransactionCurrencyMother {

  public static TransactionCurrency validTransactionCurrency() {
    return new TransactionCurrency("USD");
  }
}
