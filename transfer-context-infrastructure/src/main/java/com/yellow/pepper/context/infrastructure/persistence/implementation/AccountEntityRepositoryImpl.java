package com.yellow.pepper.context.infrastructure.persistence.implementation;

import com.yellow.pepper.context.accounts.accounts.domain.Account;
import com.yellow.pepper.context.accounts.accounts.domain.AccountNumber;
import com.yellow.pepper.context.accounts.accounts.domain.IAccountRepository;
import com.yellow.pepper.context.infrastructure.persistence.entity.AccountEntity;
import com.yellow.pepper.context.infrastructure.persistence.repository.IAccountEntityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Locale;
import java.util.Optional;

/**
 * Account entity repository implementation
 */
@Service
@Transactional
public class AccountEntityRepositoryImpl implements IAccountRepository {

  private static final String EXCHANGE_RATE_URL = "https://api.exchangeratesapi.io";
  private static final String BASE = "base";
  private final IAccountEntityRepository accountEntityRepository;
  private final RestTemplate restTemplate;

  /**
   * Constructor
   *
   * @param accountEntityRepository The account entity repository
   * @param restTemplate            The rest template
   */
  public AccountEntityRepositoryImpl(
      IAccountEntityRepository accountEntityRepository, RestTemplate restTemplate) {
    this.accountEntityRepository = accountEntityRepository;
    this.restTemplate = restTemplate;
  }

  @Override
  public Optional<Account> findByAccountNumber(AccountNumber accountNumber) {
    return accountEntityRepository.findByNumber(accountNumber.value()).map(AccountEntity::accountEntityToAccountDomain);
  }

  @Override
  public void transferMoney(Account originAccount, Account destinationAccount) {
    accountEntityRepository.save(AccountEntity.accountDomainToAccountEntity(originAccount));
    accountEntityRepository.save(AccountEntity.accountDomainToAccountEntity(destinationAccount));
  }

  @Override
  public double findCadExchangeRate(String base) {
    var url = UriComponentsBuilder
        .fromHttpUrl(EXCHANGE_RATE_URL)
        .path("/latest")
        .queryParam(BASE, base.toUpperCase(Locale.ENGLISH))
        .build().toUri();

    var rateResponse = restTemplate.getForObject(url, RateResponse.class);
    return rateResponse.getRates().getCad();
  }

  @Override
  public void saveAccount(Account account) {
    accountEntityRepository.save(AccountEntity.accountDomainToAccountEntity(account));
  }
}
