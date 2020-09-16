package com.yellow.pepper.api.domain.find;

import com.yellow.pepper.context.accounts.accounts.domain.find.FindAccountQuery;

public final class FindAccountQueryMother {

  public static FindAccountQuery valid() {
    return FindAccountQuery.builder()
        .accountNumber("12345")
        .build();
  }
}
