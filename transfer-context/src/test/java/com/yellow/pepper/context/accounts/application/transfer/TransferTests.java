package com.yellow.pepper.context.accounts.application.transfer;

import com.yellow.pepper.context.accounts.accounts.application.transfer.Transfer;
import com.yellow.pepper.context.accounts.accounts.domain.Account;
import com.yellow.pepper.context.accounts.accounts.domain.AccountNumber;
import com.yellow.pepper.context.accounts.accounts.domain.IAccountRepository;
import com.yellow.pepper.context.accounts.accounts.domain.exception.AccountDoesNotExistException;
import com.yellow.pepper.context.accounts.accounts.domain.exception.AccountNumberShouldNotBeNullOrEmptyException;
import com.yellow.pepper.context.accounts.accounts.domain.exception.OriginAccountDoesNotHaveEnoughBalanceException;
import com.yellow.pepper.context.accounts.accounts.domain.exception.TransferLimitExceededException;
import com.yellow.pepper.context.accounts.accounts.domain.transfer.ITransfer;
import com.yellow.pepper.context.accounts.domain.AccountMother;
import com.yellow.pepper.context.accounts.domain.transfer.TransferCommandMother;
import com.yellow.pepper.context.accounts.shared.domain.exception.TransactionCurrencyIsNotAllowedException;
import com.yellow.pepper.context.accounts.shared.domain.exception.TransferAmountMustBeGreaterThanZeroException;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class TransferTests {

  private final ITransfer transfer;
  @Mock
  private IAccountRepository accountRepository;

  public TransferTests() {
    initMocks(this);
    this.transfer = new Transfer(accountRepository);
  }

  @Test
  public void Transfer_One_Hundred_From_Origin_To_Destination_Account() {
    final double cadValue = 1.3247;
    final double minTax = 0.002;

    var transferCommand = TransferCommandMother.valid1TransferCommandWithOneHundred();
    var originAccount = AccountMother.accountWithTwoHundred();
    var destinationAccount = AccountMother.anotherAccountWithOneHundredAndOneTransfer();

    when(accountRepository.findByAccountNumber(originAccount.get().getAccountNumber())).thenReturn(originAccount);
    when(accountRepository.findByAccountNumber(destinationAccount.get().getAccountNumber()))
        .thenReturn(destinationAccount);
    when(accountRepository.findCadExchangeRate("USD")).thenReturn(cadValue);

    var result = transfer.Execute(transferCommand);

    assertNotNull(result);
    assertThat(result.getTaxCollected()).isEqualTo(transferCommand.getAmount() * minTax);
    assertThat(result.getCad()).isEqualTo(cadValue * transferCommand.getAmount() * minTax);

    //validate final balances from origin and destination accounts
    ArgumentCaptor<Account> captorOriginAccount = ArgumentCaptor.forClass(Account.class);
    ArgumentCaptor<Account> captorDestinationAccount = ArgumentCaptor.forClass(Account.class);
    verify(accountRepository).transferMoney(captorOriginAccount.capture(), captorDestinationAccount.capture());

    var originAccountResult = captorOriginAccount.getValue();
    var destinationAccountResult = captorDestinationAccount.getValue();

    var expectedOriginAccountBalance =
        AccountMother.accountWithTwoHundred().get().getAccountBalance().value() - transferCommand.getAmount() -
            result.getTaxCollected();

    var expectedDestinationAccountBalance =
        AccountMother.anotherAccountWithOneHundredAndOneTransfer().get().getAccountBalance().value() +
            transferCommand.getAmount();

    assertThat(originAccountResult.getAccountBalance().value()).isEqualTo(expectedOriginAccountBalance);
    assertThat(destinationAccountResult.getAccountBalance().value()).isEqualTo(expectedDestinationAccountBalance);

    verify(accountRepository).findByAccountNumber(originAccount.get().getAccountNumber());
    verify(accountRepository).findByAccountNumber(destinationAccount.get().getAccountNumber());
    verify(accountRepository).findCadExchangeRate("USD");
    verify(accountRepository).transferMoney(originAccount.get(), destinationAccount.get());
  }

  @Test
  public void Transfer_Two_Hundred_From_Origin_To_Destination_Account() {
    final double cadValue = 1.3247;
    final double minTax = 0.005;

    var transferCommand = TransferCommandMother.valid1TransferCommandWithTwoHundred();
    var originAccount = AccountMother.accountWithThreeHundred();
    var destinationAccount = AccountMother.anotherAccountWithOneHundredAndOneTransfer();

    when(accountRepository.findByAccountNumber(originAccount.get().getAccountNumber())).thenReturn(originAccount);
    when(accountRepository.findByAccountNumber(destinationAccount.get().getAccountNumber()))
        .thenReturn(destinationAccount);
    when(accountRepository.findCadExchangeRate("USD")).thenReturn(cadValue);

    var result = transfer.Execute(transferCommand);

    assertNotNull(result);
    assertThat(result.getTaxCollected()).isEqualTo(transferCommand.getAmount() * minTax);
    assertThat(result.getCad()).isEqualTo(cadValue * transferCommand.getAmount() * minTax);

    //validate final balances from origin and destination accounts
    ArgumentCaptor<Account> captorOriginAccount = ArgumentCaptor.forClass(Account.class);
    ArgumentCaptor<Account> captorDestinationAccount = ArgumentCaptor.forClass(Account.class);
    verify(accountRepository).transferMoney(captorOriginAccount.capture(), captorDestinationAccount.capture());

    var originAccountResult = captorOriginAccount.getValue();
    var destinationAccountResult = captorDestinationAccount.getValue();

    var expectedOriginAccountBalance =
        AccountMother.accountWithThreeHundred().get().getAccountBalance().value() - transferCommand.getAmount() -
            result.getTaxCollected();

    var expectedDestinationAccountBalance =
        AccountMother.anotherAccountWithOneHundredAndOneTransfer().get().getAccountBalance().value() +
            transferCommand.getAmount();

    assertThat(originAccountResult.getAccountBalance().value()).isEqualTo(expectedOriginAccountBalance);
    assertThat(destinationAccountResult.getAccountBalance().value()).isEqualTo(expectedDestinationAccountBalance);

    verify(accountRepository).findByAccountNumber(originAccount.get().getAccountNumber());
    verify(accountRepository).findByAccountNumber(destinationAccount.get().getAccountNumber());
    verify(accountRepository).findCadExchangeRate("USD");
    verify(accountRepository).transferMoney(originAccount.get(), destinationAccount.get());
  }

  @Test
  public void Transfer_With_Invalid_Currency() {
    var transferCommand = TransferCommandMother.transferCommandWithInvalidCurrency();

    var exception = assertThrows(TransactionCurrencyIsNotAllowedException.class,
        () -> transfer.Execute(transferCommand), "Validate the currency not allowed exception");

    assertThat(exception.getError().getError()).isEqualTo("TransactionCurrencyIsNotAllowedException");
    assertThat(exception.getError().getDescription())
        .isEqualTo(String.format("The transaction currency <%s> is not allowed", transferCommand.getCurrency()));
    assertThat(exception.getError().getCode()).isEqualTo("1008");
  }

  @Test
  public void Transfer_With_Invalid_Amount() {
    var transferCommand = TransferCommandMother.transferCommandWithInvalidAmount();

    var exception = assertThrows(TransferAmountMustBeGreaterThanZeroException.class,
        () -> transfer.Execute(transferCommand), "Validate the invalid amount exception");

    assertThat(exception.getError().getError()).isEqualTo("TransferAmountMustBeGreaterThanZeroException");
    assertThat(exception.getError().getDescription())
        .isEqualTo("The transfer amount must be greater than zero");
    assertThat(exception.getError().getCode()).isEqualTo("1005");
  }

  @Test
  public void Transfer_With_Invalid_Account() {
    var transferCommand = TransferCommandMother.transferCommandWithInvalidAccount();

    var exception = assertThrows(AccountNumberShouldNotBeNullOrEmptyException.class,
        () -> transfer.Execute(transferCommand), "Validate the invalid account exception");

    assertThat(exception.getError().getError()).isEqualTo("AccountNumberShouldNotBeNullOrEmptyException");
    assertThat(exception.getError().getDescription())
        .isEqualTo("The account number should not be null or empty");
    assertThat(exception.getError().getCode()).isEqualTo("1000");
  }

  @Test
  public void Transfer_When_Origin_Account_Does_Not_Exist() {
    var transferCommand = TransferCommandMother.valid1TransferCommandWithOneHundred();

    when(accountRepository.findByAccountNumber(any(AccountNumber.class))).thenReturn(Optional.empty());

    var exception = assertThrows(AccountDoesNotExistException.class,
        () -> transfer.Execute(transferCommand), "Validate the account does not exist exception");

    assertThat(exception.getError().getError()).isEqualTo("AccountDoesNotExistException");
    assertThat(exception.getError().getDescription())
        .isEqualTo(String.format("The account with number <%s> does not exist", transferCommand.getOriginAccount()));
    assertThat(exception.getError().getCode()).isEqualTo("1003");
  }

  @Test
  public void Transfer_When_Destination_Account_Does_Not_Exist() {
    var transferCommand = TransferCommandMother.valid1TransferCommandWithOneHundred();
    var originAccount = AccountMother.accountWithOneHundredAndOneTransfer();
    var destinationAccount = AccountMother.anotherAccountWithOneHundredAndOneTransfer();

    when(accountRepository.findByAccountNumber(originAccount.get().getAccountNumber())).thenReturn(originAccount);
    when(accountRepository.findByAccountNumber(destinationAccount.get().getAccountNumber()))
        .thenReturn(Optional.empty());

    var exception = assertThrows(AccountDoesNotExistException.class,
        () -> transfer.Execute(transferCommand), "Validate the account does not exist exception");

    assertThat(exception.getError().getError()).isEqualTo("AccountDoesNotExistException");
    assertThat(exception.getError().getDescription())
        .isEqualTo(
            String.format("The account with number <%s> does not exist", transferCommand.getDestinationAccount()));
    assertThat(exception.getError().getCode()).isEqualTo("1003");
  }

  @Test
  public void Transfer_When_Account_Has_Exceeded_Max_Transfer_Per_Day() {
    var transferCommand = TransferCommandMother.valid1TransferCommandWithOneHundred();
    var originAccount = AccountMother.accountWithOneHundredAndThreeTransfers();

    when(accountRepository.findByAccountNumber(any(AccountNumber.class))).thenReturn(originAccount);

    var exception = assertThrows(TransferLimitExceededException.class,
        () -> transfer.Execute(transferCommand), "Validate the origin account exceeded exception");

    assertThat(exception.getError().getError()).isEqualTo("TransferLimitExceededException");
    assertThat(exception.getError().getDescription())
        .isEqualTo("The transfer limit has been exceeded");
    assertThat(exception.getError().getCode()).isEqualTo("1009");
  }

  @Test
  public void Transfer_When_Origin_Account_Does_Not_Have_Enough_Balance() {
    var transferCommand = TransferCommandMother.valid2TransferCommandWithTwoHundred();
    var originAccount = AccountMother.anotherAccountWithOneHundredAndOneTransfer();

    when(accountRepository.findByAccountNumber(any(AccountNumber.class))).thenReturn(originAccount);

    var exception = assertThrows(OriginAccountDoesNotHaveEnoughBalanceException.class,
        () -> transfer.Execute(transferCommand),
        "Validate the origin account does not have enough balance exception");

    assertThat(exception.getError().getError()).isEqualTo("OriginAccountDoesNotHaveEnoughBalanceException");
    assertThat(exception.getError().getDescription())
        .isEqualTo(String.format("The account with number <%s> does not have enough balance",
            originAccount.get().getAccountNumber().value()));
    assertThat(exception.getError().getCode()).isEqualTo("1004");
  }
}
