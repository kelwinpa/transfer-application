package com.yellow.pepper.api.controller.accounts.create;

import com.yellow.pepper.api.controller.ResponseExceptionHandler;
import com.yellow.pepper.api.domain.create.CreateAccountCommandMother;
import com.yellow.pepper.context.accounts.accounts.domain.exception.AccountAlreadyExistsException;
import com.yellow.pepper.context.accounts.accounts.domain.exception.AccountCurrencyIsNotAllowedException;
import com.yellow.pepper.context.accounts.accounts.domain.exception.AccountNumberShouldNotBeNullOrEmptyException;
import com.yellow.pepper.context.accounts.accounts.domain.exception.AccountShouldNotHaveNegativeBalanceException;
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
import static org.springframework.http.HttpStatus.CREATED;

public class CreateAccountControllerTests {

  @Mock
  private CommandBus commandBus;

  @InjectMocks
  private CreateAccountController createAccountController;

  @InjectMocks
  private ResponseExceptionHandler responseExceptionHandler;

  public CreateAccountControllerTests() {
    initMocks(this);
    RestAssuredMockMvc.standaloneSetup(createAccountController, responseExceptionHandler);
  }

  @Test
  public void Respond_201_When_Create_Account() {

    final var payload = "{\n" +
        "  \"accountNumber\": \"123456\",\n" +
        "  \"currency\": \"USD\",\n" +
        "  \"initialBalance\": 0\n" +
        "}";

    given()
        .contentType(JSON)
        .body(payload)
        .when()
        .post("/accounts")
        .then()
        .statusCode(CREATED.value())
        .contentType(JSON)
        .body("status", equalTo("OK"));
  }

  @Test
  public void Respond_409_When_Account_Already_Exists() {
    var createAccountCommand = CreateAccountCommandMother.valid();

    when(commandBus.execute(createAccountCommand)).thenThrow(AccountAlreadyExistsException.class);

    final var payload = "{\n" +
        "  \"accountNumber\": \"12345\",\n" +
        "  \"currency\": \"USD\",\n" +
        "  \"initialBalance\": 0\n" +
        "}";

    given()
        .contentType(JSON)
        .body(payload)
        .when()
        .post("/accounts")
        .then()
        .statusCode(CONFLICT.value());
  }

  @Test
  public void Respond_400_When_Account_Number_Is_Null() {
    var createAccountCommand = CreateAccountCommandMother.invalid();

    when(commandBus.execute(createAccountCommand)).thenThrow(AccountNumberShouldNotBeNullOrEmptyException.class);

    final var payload = "{\n" +
        "  \"accountNumber\": \"\",\n" +
        "  \"currency\": \"COP\",\n" +
        "  \"initialBalance\": -1\n" +
        "}";

    given()
        .contentType(JSON)
        .body(payload)
        .when()
        .post("/accounts")
        .then()
        .statusCode(BAD_REQUEST.value());
  }

  @Test
  public void Respond_400_When_Account_Currency_Is_Not_Allowed() {
    var createAccountCommand = CreateAccountCommandMother.invalid();

    when(commandBus.execute(createAccountCommand)).thenThrow(AccountCurrencyIsNotAllowedException.class);

    final var payload = "{\n" +
        "  \"accountNumber\": \"\",\n" +
        "  \"currency\": \"COP\",\n" +
        "  \"initialBalance\": -1\n" +
        "}";

    given()
        .contentType(JSON)
        .body(payload)
        .when()
        .post("/accounts")
        .then()
        .statusCode(BAD_REQUEST.value());
  }

  @Test
  public void Respond_400_When_Account_Has_A_Negative_Balance() {
    var createAccountCommand = CreateAccountCommandMother.invalid();

    when(commandBus.execute(createAccountCommand)).thenThrow(AccountShouldNotHaveNegativeBalanceException.class);

    final var payload = "{\n" +
        "  \"accountNumber\": \"\",\n" +
        "  \"currency\": \"COP\",\n" +
        "  \"initialBalance\": -1\n" +
        "}";

    given()
        .contentType(JSON)
        .body(payload)
        .when()
        .post("/accounts")
        .then()
        .statusCode(BAD_REQUEST.value());
  }

}
