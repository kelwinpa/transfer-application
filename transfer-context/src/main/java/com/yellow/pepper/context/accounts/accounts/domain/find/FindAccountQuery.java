package com.yellow.pepper.context.accounts.accounts.domain.find;

import com.yellow.pepper.context.accounts.accounts.domain.Account;
import com.yellow.pepper.context.accounts.shared.domain.IQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Find account query
 */
@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class FindAccountQuery implements IQuery<Account> {
  private String accountNumber;
}
