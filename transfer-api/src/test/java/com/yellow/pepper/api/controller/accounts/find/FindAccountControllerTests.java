package com.yellow.pepper.api.controller.accounts.find;

import com.yellow.pepper.api.controller.ResponseExceptionHandler;
import com.yellow.pepper.api.domain.AccountMother;
import com.yellow.pepper.api.domain.find.FindAccountQueryMother;
import com.yellow.pepper.context.accounts.accounts.domain.exception.AccountDoesNotExistException;
import de.triology.cb.CommandBus;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.OK;

public class FindAccountControllerTests {

  @Mock
  private CommandBus queryBus;

  @InjectMocks
  private FindAccountController findAccountController;

  @InjectMocks
  private ResponseExceptionHandler responseExceptionHandler;

  public FindAccountControllerTests() {
    initMocks(this);
    RestAssuredMockMvc.standaloneSetup(findAccountController, responseExceptionHandler);
  }

  @Test
  public void Find_Account() {

    var findAccountQuery = FindAccountQueryMother.valid();
    var account = AccountMother.accountWithTwoHundred().get();

    when(queryBus.execute(eq(findAccountQuery))).thenReturn(account);

    given()
        .contentType(JSON)
        .when()
        .get("accounts/12345")
        .then()
        .statusCode(OK.value())
        .contentType(JSON)
        .body("accountNumber", equalTo(account.getAccountNumber().value()))
        .body("currency", equalTo(account.getAccountCurrency().value()))
        .body("transfers", CoreMatchers.notNullValue());
  }

  @Test
  public void Respond_409_When_Account_Does_Not_Exist(){
    var findAccountQuery = FindAccountQueryMother.valid();

    when(queryBus.execute(eq(findAccountQuery))).thenThrow(AccountDoesNotExistException.class);

    given()
        .contentType(JSON)
        .when()
        .get("accounts/12345")
        .then()
        .statusCode(CONFLICT.value());
  }
}
