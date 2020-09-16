package com.yellow.pepper.api.configuration;

import com.yellow.pepper.context.accounts.accounts.application.create.CreateAccount;
import com.yellow.pepper.context.accounts.accounts.application.create.CreateAccountCommandHandler;
import com.yellow.pepper.context.accounts.accounts.application.find.FindAccount;
import com.yellow.pepper.context.accounts.accounts.application.find.FindAccountQueryHandler;
import com.yellow.pepper.context.accounts.accounts.application.transfer.Transfer;
import com.yellow.pepper.context.accounts.accounts.application.transfer.TransferCommandHandler;
import com.yellow.pepper.context.accounts.accounts.domain.IAccountRepository;
import com.yellow.pepper.context.accounts.accounts.domain.create.ICreateAccount;
import com.yellow.pepper.context.accounts.accounts.domain.find.IFindAccount;
import com.yellow.pepper.context.accounts.accounts.domain.transfer.ITransfer;
import de.triology.cb.CommandBus;
import de.triology.cb.decorator.LoggingCommandBus;
import de.triology.cb.spring.Registry;
import de.triology.cb.spring.SpringCommandBus;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Command bus configuration class
 */
@Configuration
public class CommandBusConfiguration {

  /**
   * Command Bus bean declaration.
   *
   * @param applicationContext the application context
   * @return Command Bus
   */
  @Bean
  public CommandBus commandBus(ApplicationContext applicationContext) {
    return new LoggingCommandBus(new SpringCommandBus(new Registry(applicationContext)));
  }

  /**
   * Create account command handler bean declaration
   *
   * @param createAccount The create account service
   * @return Create account command handler
   */
  @Bean
  public CreateAccountCommandHandler createAccountCommandHandler(ICreateAccount createAccount) {
    return new CreateAccountCommandHandler(createAccount);
  }

  /**
   * Create account bean declaration
   *
   * @param accountRepository The account repository
   * @return Create account interface
   */
  @Bean
  public ICreateAccount createAccount(IAccountRepository accountRepository) {
    return new CreateAccount(accountRepository);
  }

  /**
   * Find account query handler bean declaration
   *
   * @param findAccount The find account service
   * @return Find account query handler
   */
  @Bean
  public FindAccountQueryHandler findAccountQueryHandler(IFindAccount findAccount) {
    return new FindAccountQueryHandler(findAccount);
  }

  /**
   * Find account bean declaration
   *
   * @param accountRepository The account repository
   * @return Find account interface
   */
  @Bean
  public IFindAccount findAccount(IAccountRepository accountRepository) {
    return new FindAccount(accountRepository);
  }

  /**
   * Transfer command handler bean declaration
   *
   * @param transfer The transfer service
   * @return Transfer command handler
   */
  @Bean
  public TransferCommandHandler transferCommandHandler(ITransfer transfer) {
    return new TransferCommandHandler(transfer);
  }

  /**
   * Transfer bean declaration
   *
   * @param accountRepository The account repository
   * @return The transfer interface
   */
  @Bean
  public ITransfer transfer(IAccountRepository accountRepository) {
    return new Transfer(accountRepository);
  }

}
