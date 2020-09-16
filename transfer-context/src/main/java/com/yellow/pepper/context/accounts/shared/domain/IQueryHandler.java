package com.yellow.pepper.context.accounts.shared.domain;

import de.triology.cb.CommandHandler;

public interface IQueryHandler<R, C extends IQuery<R>> extends CommandHandler<R, C> {
}
