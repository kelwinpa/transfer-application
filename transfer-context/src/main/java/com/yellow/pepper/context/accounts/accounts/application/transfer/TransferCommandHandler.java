package com.yellow.pepper.context.accounts.accounts.application.transfer;

import com.yellow.pepper.context.accounts.accounts.domain.transfer.ITransfer;
import com.yellow.pepper.context.accounts.accounts.domain.transfer.TransferCommand;
import com.yellow.pepper.context.accounts.shared.domain.ICommandHandler;

/**
 * Transfer command handler
 */
public class TransferCommandHandler implements ICommandHandler<TransferDto, TransferCommand> {

  private final ITransfer transfer;

  /**
   * Constructor
   *
   * @param transfer The transfer interface
   */
  public TransferCommandHandler(ITransfer transfer) {
    this.transfer = transfer;
  }

  @Override
  public TransferDto handle(TransferCommand transferCommand) {
    return transfer.Execute(transferCommand);
  }
}
