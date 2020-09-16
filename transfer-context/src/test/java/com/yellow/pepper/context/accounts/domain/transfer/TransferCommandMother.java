package com.yellow.pepper.context.accounts.domain.transfer;

import com.yellow.pepper.context.accounts.accounts.domain.transfer.TransferCommand;

public final class TransferCommandMother {

  public static TransferCommand valid1TransferCommandWithOneHundred() {
    return TransferCommand.builder()
        .currency("USD")
        .amount(100)
        .originAccount("12345")
        .destinationAccount("54321")
        .description("transfer description")
        .build();
  }

  public static TransferCommand valid1TransferCommandWithTwoHundred() {
    return TransferCommand.builder()
        .currency("USD")
        .amount(200)
        .originAccount("12345")
        .destinationAccount("54321")
        .description("transfer description")
        .build();
  }

  public static TransferCommand valid2TransferCommandWithTwoHundred() {
    return TransferCommand.builder()
        .currency("USD")
        .amount(200)
        .originAccount("54321")
        .destinationAccount("98765")
        .description("transfer description")
        .build();
  }

  public static TransferCommand transferCommandWithInvalidCurrency() {
    return TransferCommand.builder()
        .currency("COP")
        .amount(100)
        .originAccount("12345")
        .destinationAccount("54321")
        .description("transfer description")
        .build();
  }

  public static TransferCommand transferCommandWithInvalidAmount() {
    return TransferCommand.builder()
        .currency("USD")
        .amount(-100)
        .originAccount("12345")
        .destinationAccount("54321")
        .description("transfer description")
        .build();
  }

  public static TransferCommand transferCommandWithInvalidAccount() {
    return TransferCommand.builder()
        .currency("USD")
        .amount(100)
        .originAccount("")
        .destinationAccount(null)
        .description("transfer description")
        .build();
  }
}
