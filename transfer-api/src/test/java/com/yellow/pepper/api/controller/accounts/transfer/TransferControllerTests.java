package com.yellow.pepper.api.controller.accounts.transfer;

import com.yellow.pepper.api.controller.ResponseExceptionHandler;
import com.yellow.pepper.api.domain.transfer.TransferCommandMother;
import com.yellow.pepper.context.accounts.accounts.domain.exception.AccountDoesNotExistException;
import com.yellow.pepper.context.accounts.accounts.domain.exception.AccountNumberShouldNotBeNullOrEmptyException;
import com.yellow.pepper.context.accounts.accounts.domain.exception.OriginAccountDoesNotHaveEnoughBalanceException;
import com.yellow.pepper.context.accounts.accounts.domain.exception.TransferLimitExceededException;
import com.yellow.pepper.context.accounts.shared.domain.exception.TransactionCurrencyIsNotAllowedException;
import com.yellow.pepper.context.accounts.shared.domain.exception.TransferAmountMustBeGreaterThanZeroException;
import de.triology.cb.CommandBus;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.OK;

public class TransferControllerTests {

  @Mock
  private CommandBus commandBus;

  @InjectMocks
  private TransferController transferController;

  @InjectMocks
  private ResponseExceptionHandler responseExceptionHandler;

  public TransferControllerTests() {
    initMocks(this);
    RestAssuredMockMvc.standaloneSetup(transferController, responseExceptionHandler);
  }

  @Test
  public void Respond_200_When_Transfer_Money() {

    var transferCommand = TransferCommandMother.valid();
    var transferDto = TransferDtoMother.valid();

    when(commandBus.execute(transferCommand)).thenReturn(transferDto);

    final var payload = "{\n" +
        "  \"amount\": 10,\n" +
        "  \"currency\": \"USD\",\n" +
        "  \"description\": \"description\",\n" +
        "  \"destinationAccount\": \"54321\",\n" +
        "  \"originAccount\": \"12345\"\n" +
        "}";

    given()
        .contentType(JSON)
        .body(payload)
        .when()
        .post("/accounts/transfer")
        .then()
        .statusCode(OK.value())
        .contentType(JSON)
        .body("status", equalTo("OK"));

  }

  @Test
  public void Respond_409_When_Account_Does_Not_Exist() {
    var transferCommand = TransferCommandMother.valid();

    when(commandBus.execute(transferCommand)).thenThrow(AccountDoesNotExistException.class);

    final var payload = "{\n" +
        "  \"amount\": 10,\n" +
        "  \"currency\": \"USD\",\n" +
        "  \"description\": \"description\",\n" +
        "  \"destinationAccount\": \"54321\",\n" +
        "  \"originAccount\": \"12345\"\n" +
        "}";

    given()
        .contentType(JSON)
        .body(payload)
        .when()
        .post("/accounts/transfer")
        .then()
        .statusCode(CONFLICT.value());
  }

  @Test
  public void Respond_409_When_Origin_Account_Does_Not_Have_Enough_Balance() {
    var transferCommand = TransferCommandMother.valid();

    when(commandBus.execute(transferCommand)).thenThrow(OriginAccountDoesNotHaveEnoughBalanceException.class);

    final var payload = "{\n" +
        "  \"amount\": 10,\n" +
        "  \"currency\": \"USD\",\n" +
        "  \"description\": \"description\",\n" +
        "  \"destinationAccount\": \"54321\",\n" +
        "  \"originAccount\": \"12345\"\n" +
        "}";

    given()
        .contentType(JSON)
        .body(payload)
        .when()
        .post("/accounts/transfer")
        .then()
        .statusCode(CONFLICT.value());
  }

  @Test
  public void Respond_409_When_Transfer_Exceeded_Limit() {
    var transferCommand = TransferCommandMother.valid();

    when(commandBus.execute(transferCommand)).thenThrow(TransferLimitExceededException.class);

    final var payload = "{\n" +
        "  \"amount\": 10,\n" +
        "  \"currency\": \"USD\",\n" +
        "  \"description\": \"description\",\n" +
        "  \"destinationAccount\": \"54321\",\n" +
        "  \"originAccount\": \"12345\"\n" +
        "}";

    given()
        .contentType(JSON)
        .body(payload)
        .when()
        .post("/accounts/transfer")
        .then()
        .statusCode(CONFLICT.value());
  }

  @Test
  public void Respond_400_When_Account_Number_Is_Empty() {
    var transferCommand = TransferCommandMother.invalid();

    when(commandBus.execute(transferCommand)).thenThrow(AccountNumberShouldNotBeNullOrEmptyException.class);

    final var payload = "{\n" +
        "  \"amount\": -10,\n" +
        "  \"currency\": \"COP\",\n" +
        "  \"description\": \"description\",\n" +
        "  \"destinationAccount\": \"54321\",\n" +
        "  \"originAccount\": \"\"\n" +
        "}";

    given()
        .contentType(JSON)
        .body(payload)
        .when()
        .post("/accounts/transfer")
        .then()
        .statusCode(BAD_REQUEST.value());
  }

  @Test
  public void Respond_400_When_Transfer_Amount_Is_Negative() {
    var transferCommand = TransferCommandMother.invalid();

    when(commandBus.execute(transferCommand)).thenThrow(TransferAmountMustBeGreaterThanZeroException.class);

    final var payload = "{\n" +
        "  \"amount\": -10,\n" +
        "  \"currency\": \"COP\",\n" +
        "  \"description\": \"description\",\n" +
        "  \"destinationAccount\": \"54321\",\n" +
        "  \"originAccount\": \"\"\n" +
        "}";

    given()
        .contentType(JSON)
        .body(payload)
        .when()
        .post("/accounts/transfer")
        .then()
        .statusCode(BAD_REQUEST.value());
  }

  @Test
  public void Respond_400_When_Transfer_Currency_Is_Not_Allowed() {
    var transferCommand = TransferCommandMother.invalid();

    when(commandBus.execute(transferCommand)).thenThrow(TransactionCurrencyIsNotAllowedException.class);

    final var payload = "{\n" +
        "  \"amount\": -10,\n" +
        "  \"currency\": \"COP\",\n" +
        "  \"description\": \"description\",\n" +
        "  \"destinationAccount\": \"54321\",\n" +
        "  \"originAccount\": \"\"\n" +
        "}";

    given()
        .contentType(JSON)
        .body(payload)
        .when()
        .post("/accounts/transfer")
        .then()
        .statusCode(BAD_REQUEST.value());
  }


}
