package com.yellow.pepper.context.accounts.accounts.application.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Transfer DTO
 */
@Getter
@AllArgsConstructor
@Builder
public class TransferDto {
  private double taxCollected;
  private double cad;
}
