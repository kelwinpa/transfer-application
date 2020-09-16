package com.yellow.pepper.context.accounts.accounts.application.find;

import com.yellow.pepper.context.accounts.accounts.domain.Account;
import com.yellow.pepper.context.accounts.accounts.domain.find.FindAccountQuery;
import com.yellow.pepper.context.accounts.accounts.domain.find.IFindAccount;
import com.yellow.pepper.context.accounts.shared.domain.IQueryHandler;

/**
 * Find account query handler
 */
public class FindAccountQueryHandler implements IQueryHandler<Account, FindAccountQuery> {

  private final IFindAccount findAccount;

  /**
   * Constructor
   *
   * @param findAccount The find account interface
   */
  public FindAccountQueryHandler(IFindAccount findAccount) {
    this.findAccount = findAccount;
  }

  @Override
  public Account handle(FindAccountQuery findAccountQuery) {
    return findAccount.Execute(findAccountQuery);
  }
}
