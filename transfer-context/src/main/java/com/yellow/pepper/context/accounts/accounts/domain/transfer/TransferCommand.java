package com.yellow.pepper.context.accounts.accounts.domain.transfer;

import com.yellow.pepper.context.accounts.accounts.application.transfer.TransferDto;
import com.yellow.pepper.context.accounts.shared.domain.ICommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Transfer command
 */
@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
public class TransferCommand implements ICommand<TransferDto> {
  private String originAccount;
  private String destinationAccount;
  private String currency;
  private String description;
  private double amount;
}
