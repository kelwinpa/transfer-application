package com.yellow.pepper.api.controller.accounts.find;

import com.yellow.pepper.context.accounts.accounts.domain.AccountTransfer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Transfer response
 */
@AllArgsConstructor
@Getter
@Builder
public class TransferResponse {

  private String transferId;
  private String destinationAccountNumber;
  private double amount;
  private String date;
  private String currency;
  private String description;

  /**
   * Convert an Account transfer to transfer response
   *
   * @param accountTransfer The account transfer
   * @return A Transfer response
   */
  public static TransferResponse accountTransferToTransferResponse(AccountTransfer accountTransfer) {
    return TransferResponse.builder()
        .transferId(accountTransfer.getAccountTransferId().value())
        .destinationAccountNumber(accountTransfer.getDestinationAccountNumber().value())
        .amount(accountTransfer.getTransactionAmount().value())
        .date(accountTransfer.getTransactionDateTime().toString())
        .currency(accountTransfer.getTransactionCurrency().value())
        .description(accountTransfer.getDescription())
        .build();
  }
}
