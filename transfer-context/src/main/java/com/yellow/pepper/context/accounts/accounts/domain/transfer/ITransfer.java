package com.yellow.pepper.context.accounts.accounts.domain.transfer;

import com.yellow.pepper.context.accounts.accounts.application.transfer.TransferDto;

/**
 * Interface to transfer money
 */
public interface ITransfer {

  /**
   * Method responsible to transfer money
   *
   * @param transferCommand The transfer command
   * @return The transfer dto object
   */
  TransferDto Execute(TransferCommand transferCommand);
}
