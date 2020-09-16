package com.yellow.pepper.context.accounts.application.create;

import com.yellow.pepper.context.accounts.accounts.application.create.CreateAccount;
import com.yellow.pepper.context.accounts.accounts.domain.Account;
import com.yellow.pepper.context.accounts.accounts.domain.AccountNumber;
import com.yellow.pepper.context.accounts.accounts.domain.IAccountRepository;
import com.yellow.pepper.context.accounts.accounts.domain.create.ICreateAccount;
import com.yellow.pepper.context.accounts.accounts.domain.exception.AccountAlreadyExistsException;
import com.yellow.pepper.context.accounts.accounts.domain.exception.AccountCurrencyIsNotAllowedException;
import com.yellow.pepper.context.accounts.accounts.domain.exception.AccountNumberShouldNotBeNullOrEmptyException;
import com.yellow.pepper.context.accounts.accounts.domain.exception.AccountShouldNotHaveNegativeBalanceException;
import com.yellow.pepper.context.accounts.domain.AccountMother;
import com.yellow.pepper.context.accounts.domain.create.CreateAccountCommandMother;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CreateAccountTests {

  private final ICreateAccount createAccount;
  @Mock
  private IAccountRepository accountRepository;

  public CreateAccountTests() {
    initMocks(this);
    this.createAccount = new CreateAccount(accountRepository);
  }

  @Test
  public void Create_Account() {
    var createAccountCommand = CreateAccountCommandMother.valid();

    when(accountRepository.findByAccountNumber(any(AccountNumber.class))).thenReturn(Optional.empty());

    createAccount.Execute(createAccountCommand);

    verify(accountRepository).findByAccountNumber(any(AccountNumber.class));
    verify(accountRepository).saveAccount(any(Account.class));
  }

  @Test
  public void Create_Account_With_Invalid_Account_Number() {
    var createAccountCommand = CreateAccountCommandMother.invalidAccountNumber();

    var exception = assertThrows(AccountNumberShouldNotBeNullOrEmptyException.class,
        () -> createAccount.Execute(createAccountCommand), "Validate the invalid account number exception");

    assertThat(exception.getError().getError()).isEqualTo("AccountNumberShouldNotBeNullOrEmptyException");
    assertThat(exception.getError().getDescription())
        .isEqualTo("The account number should not be null or empty");
    assertThat(exception.getError().getCode()).isEqualTo("1000");
  }

  @Test
  public void Create_Account_With_Invalid_Account_Currency() {
    var createAccountCommand = CreateAccountCommandMother.invalidAccountCurrency();

    var exception = assertThrows(AccountCurrencyIsNotAllowedException.class,
        () -> createAccount.Execute(createAccountCommand), "Validate the invalid account currency exception");

    assertThat(exception.getError().getError()).isEqualTo("AccountCurrencyIsNotAllowedException");
    assertThat(exception.getError().getDescription())
        .isEqualTo(String.format("The account currency <%s> is not allowed", createAccountCommand.getCurrency()));
    assertThat(exception.getError().getCode()).isEqualTo("1002");
  }

  @Test
  public void Create_Account_With_Invalid_Account_Balance() {
    var createAccountCommand = CreateAccountCommandMother.invalidAccountBalance();

    var exception = assertThrows(AccountShouldNotHaveNegativeBalanceException.class,
        () -> createAccount.Execute(createAccountCommand), "Validate the invalid account balance exception");

    assertThat(exception.getError().getError()).isEqualTo("AccountShouldNotHaveNegativeBalanceException");
    assertThat(exception.getError().getDescription())
        .isEqualTo("The account should not have negative balances");
    assertThat(exception.getError().getCode()).isEqualTo("1001");
  }

  @Test
  public void Create_Account_When_Account_Number_Already_Exists() {
    var createAccountCommand = CreateAccountCommandMother.valid();
    var account = AccountMother.accountWithTwoHundred();

    when(accountRepository.findByAccountNumber(any(AccountNumber.class))).thenReturn(account);

    var exception = assertThrows(AccountAlreadyExistsException.class,
        () -> createAccount.Execute(createAccountCommand), "Validate the account already exists exception");

    assertThat(exception.getError().getError()).isEqualTo("AccountAlreadyExistsException");
    assertThat(exception.getError().getDescription())
        .isEqualTo(String.format("The account with number <%s> already exists", createAccountCommand.getNewAccount()));
    assertThat(exception.getError().getCode()).isEqualTo("1010");
  }
}
