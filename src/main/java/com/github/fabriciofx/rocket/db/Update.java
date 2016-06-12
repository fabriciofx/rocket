package com.github.fabriciofx.rocket.db;

public final class Update extends Alteracao implements Comando {
	public Update(final String sql, final Object... args) {
		super(sql, args);
	}
}
