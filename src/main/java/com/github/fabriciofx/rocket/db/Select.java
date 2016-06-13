package com.github.fabriciofx.rocket.db;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class Select implements Query<Data> {
	private final transient String sql;
	private final transient Object[] args;

	public Select(final String sql, final Object... args) {
		this.sql = sql;
		this.args = Arrays.copyOf(args, args.length);
	}

	@Override
	public Data execute(final Connection connection) throws IOException {
		try {
			final PreparedStatement pstmt = connection.statement(sql);
			prepare(pstmt, args);
			final ResultSet rs = pstmt.executeQuery();
			final Data data = data(rs);
			pstmt.close();
			return data;
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

	private Data data(final ResultSet rs) throws SQLException {
		final ResultSetMetaData rsmd = rs.getMetaData();
		final int columns = rsmd.getColumnCount();
		final List<Map<String, Object>> table = new LinkedList<>();
		while (rs.next()) {
			final Map<String, Object> lines = new HashMap<>();
			for (int col = 1; col <= columns; col++) {
				lines.put(rsmd.getColumnName(col).toLowerCase(),
						rs.getObject(col));
			}
			table.add(lines);
		}
		return new Data(table);
	}
}
