package com.github.fabriciofx.rocket.db;

public final class Insert extends Alteracao implements Comando {
	public Insert(final String sql, final Object... args) {
		super(sql, args);
	}
}
