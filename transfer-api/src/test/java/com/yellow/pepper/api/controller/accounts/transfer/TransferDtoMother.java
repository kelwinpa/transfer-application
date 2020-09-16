package com.yellow.pepper.api.controller.accounts.transfer;

import com.yellow.pepper.context.accounts.accounts.application.transfer.TransferDto;

public final class TransferDtoMother {

  public static TransferDto valid() {
    return TransferDto.builder()
        .taxCollected(0.019)
        .cad(0.002)
        .build();
  }
}
