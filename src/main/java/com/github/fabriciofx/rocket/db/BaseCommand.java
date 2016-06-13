package com.github.fabriciofx.rocket.db;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public abstract class BaseCommand implements Command {
	private final transient String sql;
	private final transient Object[] args;

	public BaseCommand(final String sql, final Object... args) {
		this.sql = sql;
		this.args = Arrays.copyOf(args, args.length);
	}

	@Override
	public void execute(final Connection connection) throws IOException {
		try {
			final PreparedStatement pstmt = connection.statement(sql);
			prepare(pstmt, args);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (final SQLException e) {
			throw new IOException(e);
		}
	}

	private void prepare(final PreparedStatement stmt, final Object... args)
			throws SQLException {
		int pos = 1;
		for (final Object arg : args) {
			if (arg == null) {
				stmt.setString(pos, null);
			} else if (arg instanceof Long) {
				stmt.setLong(pos, Long.class.cast(arg));
			} else if (arg instanceof Boolean) {
				stmt.setBoolean(pos, Boolean.class.cast(arg));
			} else if (arg instanceof Date) {
				stmt.setDate(pos, Date.class.cast(arg));
			} else if (arg instanceof Integer) {
				stmt.setInt(pos, Integer.class.cast(arg));
			} else if (arg instanceof byte[]) {
				stmt.setBytes(pos, byte[].class.cast(arg));
			} else {
				stmt.setString(pos, arg.toString());
			}
			++pos;
		}
	}
}
