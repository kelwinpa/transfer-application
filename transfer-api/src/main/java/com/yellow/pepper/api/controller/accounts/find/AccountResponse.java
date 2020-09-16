package com.yellow.pepper.api.controller.accounts.find;

import com.yellow.pepper.context.accounts.accounts.domain.Account;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Find account response
 */
@AllArgsConstructor
@Getter
@Builder
@ApiModel(value = "Find account response")
public class AccountResponse {

  private String accountNumber;
  private String currency;
  private double balance;
  private Set<TransferResponse> transfers;

  /**
   * Account to Account response
   *
   * @param account The account domain
   * @return The account response
   */
  public static AccountResponse accountToAccountResponse(Account account) {
    return AccountResponse.builder()
        .accountNumber(account.getAccountNumber().value())
        .currency(account.getAccountCurrency().value())
        .balance(account.getAccountBalance().value())
        .transfers(
            account.getAccountTransfers().stream().map(TransferResponse::accountTransferToTransferResponse).collect(
                Collectors.toSet()))
        .build();
  }

}
