package com.yellow.pepper.api.controller.accounts.create;

import com.yellow.pepper.context.accounts.shared.domain.exception.TransferError;
import de.triology.cb.CommandBus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create account controller
 */
@Api(tags = "accounts")
@RestController
public class CreateAccountController {

  private final CommandBus commandBus;

  /**
   * Constructor
   *
   * @param commandBus The command bus
   */
  public CreateAccountController(CommandBus commandBus) {
    this.commandBus = commandBus;
  }

  /**
   * Create account endpoint
   *
   * @param createAccountRequest The create account request
   * @return The create account response
   */
  @ApiOperation(value = "Create an account",
      notes = "By passing in the appropriate options, you can create an account ",
      nickname = "createAccount")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Account created", response = CreateAccountResponse.class),
      @ApiResponse(code = 400, message = "Invalid input, object invalid", response = TransferError.class),
      @ApiResponse(code = 409, message = "The account already exists", response = TransferError.class)
  })
  @PostMapping(value = "accounts", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CreateAccountResponse> createAccount(
      @ApiParam(value = "Account to Create", required = true) @Validated
      @RequestBody CreateAccountRequest createAccountRequest) {

    var createAccountCommand = createAccountRequest.convertToCreateAccountCommand();

    commandBus.execute(createAccountCommand);

    return new ResponseEntity<>(CreateAccountResponse.builder().status("OK").build(), HttpStatus.CREATED);
  }

}
