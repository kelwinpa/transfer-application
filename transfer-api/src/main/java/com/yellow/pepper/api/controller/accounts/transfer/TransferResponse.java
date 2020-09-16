package com.yellow.pepper.api.controller.accounts.transfer;

import com.yellow.pepper.context.accounts.accounts.application.transfer.TransferDto;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Transfer response
 */
@AllArgsConstructor
@Getter
@Builder
@ApiModel(value = "Transfer money response")
public class TransferResponse {
  private String status;
  private double taxCollected;
  private double CAD;

  public static TransferResponse convertTransferDtoToTransferResponse(TransferDto transferDto) {
    return TransferResponse.builder()
        .status("OK")
        .taxCollected(transferDto.getTaxCollected())
        .CAD(transferDto.getCad())
        .build();
  }
}
