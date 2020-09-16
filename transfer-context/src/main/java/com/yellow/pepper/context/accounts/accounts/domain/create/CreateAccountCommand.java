package com.yellow.pepper.context.accounts.accounts.domain.create;

import com.yellow.pepper.context.accounts.shared.domain.ICommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Create account command
 */
@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
public class CreateAccountCommand implements ICommand<Void> {
  private String newAccount;
  private String currency;
  private double initialBalance;
}
