package com.yellow.pepper.api.controller.accounts.find;

import com.yellow.pepper.context.accounts.accounts.domain.find.FindAccountQuery;
import com.yellow.pepper.context.accounts.shared.domain.exception.TransferError;
import de.triology.cb.CommandBus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Find account controller
 */
@Api(tags = "accounts")
@RestController
public class FindAccountController {

  private final CommandBus queryBus;

  /**
   * Constructor
   *
   * @param queryBus The query bus
   */
  public FindAccountController(CommandBus queryBus) {
    this.queryBus = queryBus;
  }

  /**
   * Find account endpoint
   *
   * @param accountNumber The account number
   * @return account response
   */
  @ApiOperation(value = "Find account by account number", notes = "Find account by account number",
      responseContainer = "AccountResponse", nickname = "findAccount")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Account", response = AccountResponse.class),
      @ApiResponse(code = 409, message = "Account not found", response = TransferError.class)
  })
  @GetMapping(value = "accounts/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AccountResponse> findAccount(@PathVariable(value = "accountNumber") String accountNumber) {
    var findAccountQuery = FindAccountQuery.builder().accountNumber(accountNumber).build();

    var account = queryBus.execute(findAccountQuery);

    return new ResponseEntity<>(AccountResponse.accountToAccountResponse(account), HttpStatus.OK);
  }
}
