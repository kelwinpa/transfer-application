package com.yellow.pepper.context.accounts.shared.domain;

import de.triology.cb.CommandHandler;

public interface ICommandHandler<R, C extends ICommand<R>> extends CommandHandler<R, C> {
}
