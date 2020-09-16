package com.yellow.pepper.context.accounts.domain.find;

import com.yellow.pepper.context.accounts.accounts.domain.find.FindAccountQuery;

public final class FindAccountQueryMother {

  public static FindAccountQuery valid() {
    return FindAccountQuery.builder()
        .accountNumber("12345")
        .build();
  }

  public static FindAccountQuery invalidAccountNumber() {
    return FindAccountQuery.builder()
        .accountNumber("")
        .build();
  }
}
