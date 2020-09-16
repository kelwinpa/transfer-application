package com.yellow.pepper.context.accounts.accounts.application.create;

import com.yellow.pepper.context.accounts.accounts.domain.create.CreateAccountCommand;
import com.yellow.pepper.context.accounts.accounts.domain.create.ICreateAccount;
import com.yellow.pepper.context.accounts.shared.domain.ICommandHandler;

/**
 * Create account command handler class
 */
public class CreateAccountCommandHandler implements ICommandHandler<Void, CreateAccountCommand> {

  private final ICreateAccount createAccount;

  /**
   * Constructor
   *
   * @param createAccount The create account interface
   */
  public CreateAccountCommandHandler(ICreateAccount createAccount) {
    this.createAccount = createAccount;
  }

  @Override
  public Void handle(CreateAccountCommand createAccountCommand) {
    return createAccount.Execute(createAccountCommand);
  }
}
