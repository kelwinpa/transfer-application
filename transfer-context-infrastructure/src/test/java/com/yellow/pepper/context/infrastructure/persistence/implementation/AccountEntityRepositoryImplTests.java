package com.yellow.pepper.context.infrastructure.persistence.implementation;

import com.yellow.pepper.context.accounts.accounts.domain.AccountNumber;
import com.yellow.pepper.context.accounts.accounts.domain.IAccountRepository;
import com.yellow.pepper.context.infrastructure.persistence.JpaTestBase;
import com.yellow.pepper.context.infrastructure.persistence.entity.AccountEntity;
import com.yellow.pepper.context.infrastructure.persistence.entity.AccountMother;
import com.yellow.pepper.context.infrastructure.persistence.entity.RateResponseMother;
import com.yellow.pepper.context.infrastructure.persistence.repository.IAccountEntityRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class AccountEntityRepositoryImplTests extends JpaTestBase {

  @Autowired
  private IAccountRepository accountRepositoryImpl;

  @Autowired
  private IAccountEntityRepository accountEntityRepository;

  @MockBean
  private RestTemplate restTemplate;

  @BeforeEach
  public void setup() {
    accountEntityRepository.save(AccountEntity.accountDomainToAccountEntity(AccountMother.valid1()));
    accountEntityRepository.save(AccountEntity.accountDomainToAccountEntity(AccountMother.valid2()));
  }

  @Test
  public void Save_Account() {
    var account = AccountMother.valid3();
    accountRepositoryImpl.saveAccount(account);

    var savedAccount = accountEntityRepository.findByNumber(account.getAccountNumber().value());

    assertThat(savedAccount.isPresent()).isTrue();
    assertThat(savedAccount.get()).isEqualTo(AccountEntity.accountDomainToAccountEntity(account));
  }

  @Test
  public void Find_By_Account_Number() {
    var account = accountRepositoryImpl.findByAccountNumber(new AccountNumber("54321"));
    assertNotNull(account.get());
    assertThat(account.get().getAccountBalance().value()).isEqualTo(100);
  }

  @Test
  public void Transfer_Money() {
    var originAccount = AccountMother.valid1WithOneLessHundred();
    var destinationAccount = AccountMother.valid2WithOneMoreHundred();

    accountRepositoryImpl.transferMoney(originAccount, destinationAccount);

    var finalOriginAccount = accountEntityRepository.findByNumber(originAccount.getAccountNumber().value());
    var finalDestinationAccount = accountEntityRepository.findByNumber(destinationAccount.getAccountNumber().value());

    assertNotNull(finalOriginAccount);
    assertNotNull(finalDestinationAccount);

    assertThat(finalOriginAccount.get().getBalance()).isEqualTo(originAccount.getAccountBalance().value());
    assertThat(finalDestinationAccount.get().getBalance()).isEqualTo(originAccount.getAccountBalance().value());
  }

  @Test
  public void Find_Cad_Exchange_Rate() {
    var url = UriComponentsBuilder
        .fromHttpUrl("https://api.exchangeratesapi.io")
        .path("/latest")
        .queryParam("base", "USD")
        .build().toUri();

    var rateResponse = RateResponseMother.valid();

    when(restTemplate.getForObject(url, RateResponse.class)).thenReturn(rateResponse);

    var result = accountRepositoryImpl.findCadExchangeRate("USD");

    assertNotNull(result);
    assertThat(result).isEqualTo(rateResponse.getRates().getCad());
  }

  @AfterEach
  public void afterEach() {
    accountEntityRepository.deleteAll();
  }
}
