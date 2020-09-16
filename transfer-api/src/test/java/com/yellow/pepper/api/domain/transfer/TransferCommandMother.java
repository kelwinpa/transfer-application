package com.yellow.pepper.api.domain.transfer;

import com.yellow.pepper.context.accounts.accounts.domain.transfer.TransferCommand;

public final class TransferCommandMother {

  public static TransferCommand valid() {
    return TransferCommand.builder()
        .originAccount("12345")
        .destinationAccount("54321")
        .currency("USD")
        .amount(10.0)
        .description("description")
        .build();
  }

  public static TransferCommand invalid() {
    return TransferCommand.builder()
        .originAccount("")
        .destinationAccount("54321")
        .currency("COP")
        .amount(-10.0)
        .description("description")
        .build();
  }
}
