package com.yellow.pepper.context.infrastructure.persistence.entity;

import com.yellow.pepper.context.accounts.accounts.domain.Account;
import com.yellow.pepper.context.accounts.accounts.domain.AccountBalance;
import com.yellow.pepper.context.accounts.accounts.domain.AccountCurrency;
import com.yellow.pepper.context.accounts.accounts.domain.AccountNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class that represents the account entity
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity(name = "accounts")
@Table(name = "ACCOUNTS")
@EqualsAndHashCode
public class AccountEntity implements Serializable {
  private static final long serialVersionUID = 2928298208380233100L;

  @Id
  @Column(length = 50)
  private String number;

  @Column(length = 3, nullable = false)
  private String currency;

  @Column(nullable = false)
  private double balance;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "account_number")
  private Set<TransferEntity> transfers = new HashSet<>();

  /**
   * Convert an Account domain to an account entity
   *
   * @return An account entity
   */
  public static AccountEntity accountDomainToAccountEntity(Account account) {
    return AccountEntity.builder()
        .number(account.getAccountNumber().value())
        .currency(account.getAccountCurrency().value())
        .balance(account.getAccountBalance().value())
        .transfers(
            account.getAccountTransfers().stream().map(TransferEntity::accountTransferDomainToTransferEntity).collect(
                Collectors.toSet()))
        .build();
  }

  /**
   * Convert an Account entity to an Account domain
   *
   * @param accountEntity The account entity
   * @return The account domain
   */
  public static Account accountEntityToAccountDomain(AccountEntity accountEntity) {
    return Account.load(new AccountNumber(accountEntity.getNumber()),
        new AccountCurrency(accountEntity.getCurrency()), new AccountBalance(accountEntity.getBalance()),
        accountEntity.getTransfers().stream().map(TransferEntity::transferEntityToAccountTransferDomain).collect(
            Collectors.toSet())
    );
  }
}
