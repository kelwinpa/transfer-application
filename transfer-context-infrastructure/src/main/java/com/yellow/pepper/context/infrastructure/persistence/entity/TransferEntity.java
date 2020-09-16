package com.yellow.pepper.context.infrastructure.persistence.entity;

import com.yellow.pepper.context.accounts.accounts.domain.AccountNumber;
import com.yellow.pepper.context.accounts.accounts.domain.AccountTransfer;
import com.yellow.pepper.context.accounts.accounts.domain.AccountTransferId;
import com.yellow.pepper.context.accounts.shared.domain.TransactionAmount;
import com.yellow.pepper.context.accounts.shared.domain.TransactionCurrency;
import com.yellow.pepper.context.accounts.shared.domain.TransactionDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;

/**
 * Class that represents Transfer entity
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "transfers")
@Builder
@Table(name = "TRANSFERS")
@EqualsAndHashCode
public class TransferEntity implements Serializable {
  private static final long serialVersionUID = -5751057249502103141L;
  private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-DD HH:mm:ss");

  @Id
  @Column(length = 36)
  private String id;

  @Column(length = 50, nullable = false)
  private String destinationAccount;

  @Column(nullable = false)
  private double amount;

  @Column(nullable = false)
  private String dateTime;

  @Column(length = 3, nullable = false)
  private String currency;

  private String description;

  /**
   * Convert a transfer domain to transfer entity
   *
   * @param accountTransfer The account transfer
   * @return A transfer entity
   */
  public static TransferEntity accountTransferDomainToTransferEntity(AccountTransfer accountTransfer) {
    return TransferEntity.builder()
        .id(accountTransfer.getAccountTransferId().value())
        .destinationAccount(accountTransfer.getDestinationAccountNumber().value())
        .amount(accountTransfer.getTransactionAmount().value())
        .dateTime(accountTransfer.getTransactionDateTime().toString())
        .currency(accountTransfer.getTransactionCurrency().value())
        .description(accountTransfer.getDescription())
        .build();
  }

  /**
   * Convert a transfer entity to an account transfer domain
   *
   * @param transferEntity The transfer entity
   * @return An account transfer domain
   */
  public static AccountTransfer transferEntityToAccountTransferDomain(TransferEntity transferEntity) {
    return AccountTransfer.load(new AccountTransferId(transferEntity.getId()),
        new AccountNumber(transferEntity.getDestinationAccount()), new TransactionAmount(transferEntity.getAmount()),
        new TransactionDateTime(transferEntity.getDateTime()), new TransactionCurrency(transferEntity.getCurrency()),
        transferEntity.getDescription());
  }
}
