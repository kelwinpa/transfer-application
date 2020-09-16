package com.yellow.pepper.api.controller.accounts.transfer;

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
 * Transfer Controller
 */
@Api(tags = "accounts")
@RestController
public class TransferController {

  private final CommandBus commandBus;

  /**
   * Constructor
   *
   * @param commandBus The command bus
   */
  public TransferController(CommandBus commandBus) {
    this.commandBus = commandBus;
  }

  /**
   * Transfer money endpoint
   *
   * @param transferRequest The transfer request
   * @return A transfer response
   */
  @ApiOperation(value = "Transfer money",
      notes = "By passing in the appropriate options, you can transfer money to another account",
      nickname = "transfer")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Transfer done", response = TransferResponse.class),
      @ApiResponse(code = 400, message = "Invalid input, object invalid", response = TransferError.class),
      @ApiResponse(code = 409, message = "The account not found", response = TransferError.class)
  })
  @PostMapping(value = "accounts/transfer", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TransferResponse> transferMoney(
      @ApiParam(value = "Accounts to transfer", required = true) @Validated @RequestBody
          TransferRequest transferRequest) {

    var transferCommand = transferRequest.convertToTransferCommand();
    var transferDto = commandBus.execute(transferCommand);
    return new ResponseEntity<>(TransferResponse.convertTransferDtoToTransferResponse(transferDto), HttpStatus.OK);
  }
}
