package com.github.fabriciofx.rocket.db;

public final class Insert extends BaseCommand implements Command {
	public Insert(final String sql, final Object... args) {
		super(sql, args);
	}
}
