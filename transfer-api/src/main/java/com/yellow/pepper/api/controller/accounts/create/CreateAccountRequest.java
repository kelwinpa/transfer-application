package com.yellow.pepper.api.controller.accounts.create;

import com.yellow.pepper.context.accounts.accounts.domain.create.CreateAccountCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Create account API Request.
 */
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ApiModel(value = "Create account request")
public class CreateAccountRequest {

  @ApiModelProperty(value = "Number of the account", required = true)
  private String accountNumber;

  @ApiModelProperty(value = "Currency of the account", required = true)
  private String currency;

  @ApiModelProperty(value = "Initial balance of the account")
  private Double initialBalance;

  /**
   * Convert to create account command method
   *
   * @return A create account command object
   */
  public CreateAccountCommand convertToCreateAccountCommand() {
    return CreateAccountCommand.builder()
        .newAccount(this.getAccountNumber())
        .currency(this.getCurrency())
        .initialBalance(this.getInitialBalance() == null ? 0 : this.getInitialBalance())
        .build();
  }
}
