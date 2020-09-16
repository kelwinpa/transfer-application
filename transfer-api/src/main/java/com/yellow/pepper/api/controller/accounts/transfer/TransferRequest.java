package com.yellow.pepper.api.controller.accounts.transfer;

import com.yellow.pepper.context.accounts.accounts.domain.transfer.TransferCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Transfer API Request
 */
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ApiModel(value = "Transfer money")
public class TransferRequest {

  @ApiModelProperty(value = "Number of the origin account", required = true)
  private String originAccount;

  @ApiModelProperty(value = "Number of the destination account", required = true)
  private String destinationAccount;

  @ApiModelProperty(value = "Currency of the Transfer", required = true)
  private String currency;

  @ApiModelProperty(value = "Amount of the Transfer", required = true)
  private double amount;

  @ApiModelProperty(value = "Description of the Transfer")
  private String description;

  /**
   * Convert to transfer command
   *
   * @return A transfer command object
   */
  public TransferCommand convertToTransferCommand() {
    return TransferCommand.builder()
        .originAccount(originAccount)
        .destinationAccount(destinationAccount)
        .currency(currency)
        .amount(amount)
        .description(description)
        .build();
  }
}
