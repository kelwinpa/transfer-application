package com.yellow.pepper.context.accounts.application.find;

import com.yellow.pepper.context.accounts.accounts.application.find.FindAccount;
import com.yellow.pepper.context.accounts.accounts.domain.AccountNumber;
import com.yellow.pepper.context.accounts.accounts.domain.IAccountRepository;
import com.yellow.pepper.context.accounts.accounts.domain.exception.AccountDoesNotExistException;
import com.yellow.pepper.context.accounts.accounts.domain.exception.AccountNumberShouldNotBeNullOrEmptyException;
import com.yellow.pepper.context.accounts.accounts.domain.find.IFindAccount;
import com.yellow.pepper.context.accounts.domain.AccountMother;
import com.yellow.pepper.context.accounts.domain.find.FindAccountQueryMother;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class FindAccountTests {

  private final IFindAccount findAccount;
  @Mock
  private IAccountRepository accountRepository;

  public FindAccountTests() {
    initMocks(this);
    findAccount = new FindAccount(accountRepository);
  }

  @Test
  public void Find_Account() {
    var findAccountQuery = FindAccountQueryMother.valid();
    var account = AccountMother.accountWithTwoHundred();

    when(accountRepository.findByAccountNumber(any(AccountNumber.class))).thenReturn(account);

    var result = findAccount.Execute(findAccountQuery);

    assertNotNull(result);
    assertThat(result.getAccountCurrency()).isEqualTo(account.get().getAccountCurrency());
    assertThat(result.getAccountBalance()).isEqualTo(account.get().getAccountBalance());
    assertThat(result.getAccountTransfers()).isEqualTo(account.get().getAccountTransfers());
    assertThat(result).isEqualTo(account.get());

    verify(accountRepository).findByAccountNumber(any(AccountNumber.class));
  }

  @Test
  public void Find_Account_With_Invalid_Account_Number() {
    var findAccountQuery = FindAccountQueryMother.invalidAccountNumber();

    var exception = assertThrows(AccountNumberShouldNotBeNullOrEmptyException.class,
        () -> findAccount.Execute(findAccountQuery), "Validate the invalid account number exception");

    assertThat(exception.getError().getError()).isEqualTo("AccountNumberShouldNotBeNullOrEmptyException");
    assertThat(exception.getError().getDescription())
        .isEqualTo("The account number should not be null or empty");
    assertThat(exception.getError().getCode()).isEqualTo("1000");
  }

  @Test
  public void Find_Account_When_Account_Number_Does_Not_Exist() {
    var findAccountQuery = FindAccountQueryMother.valid();

    when(accountRepository.findByAccountNumber(any(AccountNumber.class))).thenReturn(Optional.empty());

    var exception = assertThrows(AccountDoesNotExistException.class,
        () -> findAccount.Execute(findAccountQuery), "Validate the invalid account number exception");

    assertThat(exception.getError().getError()).isEqualTo("AccountDoesNotExistException");
    assertThat(exception.getError().getDescription())
        .isEqualTo(String.format("The account with number <%s> does not exist", findAccountQuery.getAccountNumber()));
    assertThat(exception.getError().getCode()).isEqualTo("1003");
  }

}
