package com.yellow.pepper.api.controller;

import com.yellow.pepper.context.accounts.accounts.domain.exception.AccountAlreadyExistsException;
import com.yellow.pepper.context.accounts.accounts.domain.exception.AccountCurrencyIsNotAllowedException;
import com.yellow.pepper.context.accounts.accounts.domain.exception.AccountDoesNotExistException;
import com.yellow.pepper.context.accounts.accounts.domain.exception.AccountNumberShouldNotBeNullOrEmptyException;
import com.yellow.pepper.context.accounts.accounts.domain.exception.AccountShouldNotHaveNegativeBalanceException;
import com.yellow.pepper.context.accounts.accounts.domain.exception.OriginAccountDoesNotHaveEnoughBalanceException;
import com.yellow.pepper.context.accounts.accounts.domain.exception.TransferLimitExceededException;
import com.yellow.pepper.context.accounts.shared.domain.exception.TransactionCurrencyIsNotAllowedException;
import com.yellow.pepper.context.accounts.shared.domain.exception.TransferAmountMustBeGreaterThanZeroException;
import com.yellow.pepper.context.accounts.shared.domain.exception.TransferError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Response exception handler for controllers.
 */
@ControllerAdvice
@Slf4j
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(AccountDoesNotExistException.class)
	protected ResponseEntity<TransferError> handle(AccountDoesNotExistException ex) {
		log.error("AccountDoesNotExistException", ex);
		return new ResponseEntity<>(ex.getError(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(AccountNumberShouldNotBeNullOrEmptyException.class)
	protected ResponseEntity<TransferError> handle(AccountNumberShouldNotBeNullOrEmptyException ex) {
		log.error("AccountNumberShouldNotBeNullOrEmptyException", ex);
		return new ResponseEntity<>(ex.getError(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AccountCurrencyIsNotAllowedException.class)
	protected ResponseEntity<TransferError> handle(AccountCurrencyIsNotAllowedException ex) {
		log.error("AccountCurrencyIsNotAllowedException", ex);
		return new ResponseEntity<>(ex.getError(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AccountAlreadyExistsException.class)
	protected ResponseEntity<TransferError> handle(AccountAlreadyExistsException ex) {
		log.error("AccountAlreadyExistsException", ex);
		return new ResponseEntity<>(ex.getError(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(TransferAmountMustBeGreaterThanZeroException.class)
	protected ResponseEntity<TransferError> handle(TransferAmountMustBeGreaterThanZeroException ex) {
		log.error("TransferAmountMustBeGreaterThanZeroException", ex);
		return new ResponseEntity<>(ex.getError(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(TransactionCurrencyIsNotAllowedException.class)
	protected ResponseEntity<TransferError> handle(TransactionCurrencyIsNotAllowedException ex) {
		log.error("TransactionCurrencyIsNotAllowedException", ex);
		return new ResponseEntity<>(ex.getError(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(OriginAccountDoesNotHaveEnoughBalanceException.class)
	protected ResponseEntity<TransferError> handle(OriginAccountDoesNotHaveEnoughBalanceException ex) {
		log.error("OriginAccountDoesNotHaveEnoughBalanceException", ex);
		return new ResponseEntity<>(ex.getError(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(TransferLimitExceededException.class)
	protected ResponseEntity<TransferError> handle(TransferLimitExceededException ex) {
		log.error("TransferLimitExceededException", ex);
		return new ResponseEntity<>(ex.getError(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(AccountShouldNotHaveNegativeBalanceException.class)
	protected ResponseEntity<TransferError> handle(AccountShouldNotHaveNegativeBalanceException ex) {
		log.error("AccountShouldNotHaveNegativeBalanceException", ex);
		return new ResponseEntity<>(ex.getError(), HttpStatus.BAD_REQUEST);
	}
}
