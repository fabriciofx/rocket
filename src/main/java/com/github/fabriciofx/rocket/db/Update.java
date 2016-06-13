package com.github.fabriciofx.rocket.db;

public final class Update extends BaseCommand implements Command {
	public Update(final String sql, final Object... args) {
		super(sql, args);
	}
}
