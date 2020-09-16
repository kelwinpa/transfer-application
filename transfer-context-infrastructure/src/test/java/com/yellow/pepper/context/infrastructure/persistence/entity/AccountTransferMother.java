package com.yellow.pepper.context.infrastructure.persistence.entity;

import com.yellow.pepper.context.accounts.accounts.domain.AccountTransfer;

import java.util.HashSet;
import java.util.Set;

public final class AccountTransferMother {

  public static Set<AccountTransfer> oneAccountTransfer() {
    var accountTransfers = new HashSet<AccountTransfer>();
    accountTransfers.add(AccountTransfer.load(AccountTransferIdMother.validAccountTransferId(),
        AccountNumberMother.valid1AccountNumber(), TransactionAmountMother.validTransactionAmount(),
        TransactionDateTimeMother.validTransactionDateTime(),
        TransactionCurrencyMother.validTransactionCurrency(), "description 1"));

    return accountTransfers;
  }
}
