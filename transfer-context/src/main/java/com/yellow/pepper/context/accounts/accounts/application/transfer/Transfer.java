package com.yellow.pepper.context.accounts.accounts.application.transfer;

import com.yellow.pepper.context.accounts.accounts.domain.AccountNumber;
import com.yellow.pepper.context.accounts.accounts.domain.AccountTransfer;
import com.yellow.pepper.context.accounts.accounts.domain.IAccountRepository;
import com.yellow.pepper.context.accounts.accounts.domain.exception.AccountDoesNotExistException;
import com.yellow.pepper.context.accounts.accounts.domain.exception.OriginAccountDoesNotHaveEnoughBalanceException;
import com.yellow.pepper.context.accounts.accounts.domain.exception.TransferLimitExceededException;
import com.yellow.pepper.context.accounts.accounts.domain.transfer.ITransfer;
import com.yellow.pepper.context.accounts.accounts.domain.transfer.TransferCommand;
import com.yellow.pepper.context.accounts.shared.domain.TransactionAmount;
import com.yellow.pepper.context.accounts.shared.domain.TransactionCurrency;

/**
 * Transfer use case
 */
public class Transfer implements ITransfer {

  private static final double ONE_HUNDRED = 100;
  private static final double MIN_TAX = 0.002;
  private static final double MAX_TAX = 0.005;
  private final IAccountRepository accountRepository;

  /**
   * Constructor
   *
   * @param accountRepository The account repository
   */
  public Transfer(IAccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public TransferDto Execute(TransferCommand transferCommand) {

    // create a transfer amount (validate the request amount and the currency are implicit)
    var accountTransfer = AccountTransfer.build(new AccountNumber(transferCommand.getDestinationAccount()),
        new TransactionAmount(transferCommand.getAmount()),
        new TransactionCurrency(transferCommand.getCurrency()),
        transferCommand.getDescription());

    // get origin account
    var optionalOriginAccount =
        accountRepository.findByAccountNumber(new AccountNumber(transferCommand.getOriginAccount()));

    if (optionalOriginAccount.isEmpty()) {
      throw new AccountDoesNotExistException(transferCommand.getOriginAccount());
    }

    // get destination account
    var optionalDestinationAccount =
        accountRepository.findByAccountNumber(new AccountNumber(transferCommand.getDestinationAccount()));

    if (optionalDestinationAccount.isEmpty()) {
      throw new AccountDoesNotExistException(transferCommand.getDestinationAccount());
    }

    //A source account can only make 3 transfers per day
    var originAccount = optionalOriginAccount.get();
    if (originAccount.enoughTransfersPerDay()) {
      throw new TransferLimitExceededException();
    }

    // The origin account will be charged with a tax corresponding to 0.5% in case the
    //amount of the transfer is greater than $ 100USD or 0.2% otherwise
    var totalTransferAmount = calculateTotalTransferAmount(accountTransfer.getTransactionAmount().value());

    // validate whether origin account has enough balance
    if (!originAccount.hasEnoughBalance(totalTransferAmount)) {
      throw new OriginAccountDoesNotHaveEnoughBalanceException(transferCommand.getOriginAccount());
    }

    // transfer money to destination account
    var destinationAccount = optionalDestinationAccount.get();

    originAccount.prepareOriginAccountForNewTransfer(accountTransfer, totalTransferAmount);
    destinationAccount.prepareDestinationAccountForNewTransfer(accountTransfer);

    accountRepository.transferMoney(originAccount, destinationAccount);

    //Use the public API https://api.exchangeratesapi.io/latest?base=USD to add rate
    //exchange for CAD currency
    var taxCollected = round(totalTransferAmount - transferCommand.getAmount());
    double cad = accountRepository.findCadExchangeRate(transferCommand.getCurrency());

    return TransferDto.builder()
        .taxCollected(taxCollected)
        .cad(cad * taxCollected)
        .build();
  }

  /**
   * Round method
   *
   * @param value The vaule
   * @return A double value
   */
  private double round(double value) {
    long factor = (long) Math.pow(10, 4);
    value = value * factor;
    long tmp = Math.round(value);
    return (double) tmp / factor;
  }

  /**
   * Get total transfer amount method
   *
   * @param transferAmount The transfer amount
   * @return The total transfer amount
   */
  private double calculateTotalTransferAmount(double transferAmount) {
    double totalTransferAmount;
    if (transferAmount > ONE_HUNDRED) {
      totalTransferAmount = transferAmount + (transferAmount * MAX_TAX);
    } else {
      totalTransferAmount = transferAmount + (transferAmount * MIN_TAX);
    }
    return totalTransferAmount;
  }
}
