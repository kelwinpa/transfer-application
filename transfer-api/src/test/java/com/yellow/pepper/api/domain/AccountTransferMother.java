package com.yellow.pepper.api.domain;

import com.yellow.pepper.context.accounts.accounts.domain.AccountTransfer;

import java.util.HashSet;
import java.util.Set;

public final class AccountTransferMother {

  public static Set<AccountTransfer> oneAccountTransfer() {
    var accountTransfers = new HashSet<AccountTransfer>();
    accountTransfers.add(AccountTransfer.load(AccountTransferIdMother.validAccountTransferId(),
        AccountNumberMother.validAccountNumber(), TransactionAmountMother.validTransactionAmount(),
        TransactionDateTimeMother.validTransactionDateTime(),
        TransactionCurrencyMother.validTransactionCurrency(), "description 1"));

    return accountTransfers;
  }

  public static Set<AccountTransfer> threeCurrentAccountTransfers() {
    var accountTransfers = new HashSet<AccountTransfer>();
    accountTransfers.add(AccountTransfer.load(AccountTransferIdMother.validAccountTransferId(),
        AccountNumberMother.validAccountNumber(),
        TransactionAmountMother.validTransactionAmount(), TransactionDateTimeMother.validTransactionDateTime(),
        TransactionCurrencyMother.validTransactionCurrency(), "description 1"));

    accountTransfers.add(
        AccountTransfer.load(AccountTransferIdMother.validAccountTransferId(), AccountNumberMother.validAccountNumber(),
            TransactionAmountMother.validTransactionAmount(), TransactionDateTimeMother.validTransactionDateTime(),
            TransactionCurrencyMother.validTransactionCurrency(), "description 2"));

    accountTransfers.add(AccountTransfer.load(AccountTransferIdMother.valid2AccountTransferId(),
        AccountNumberMother.validAccountNumber(),
        TransactionAmountMother.validTransactionAmount(), TransactionDateTimeMother.validTransactionDateTime(),
        TransactionCurrencyMother.validTransactionCurrency(), "description 3"));

    return accountTransfers;
  }
}
