package com.github.fabriciofx.rocket.pessoa;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.jcabi.jdbc.ListOutcome;

public final class SqlTableMapping
	implements ListOutcome.Mapping<Map<String, String>> {
	@Override
	public Map<String, String> map(final ResultSet rs) throws SQLException {
		final ResultSetMetaData rsmd = rs.getMetaData();
		final int columns = rsmd.getColumnCount();
		final Map<String, String> table = new HashMap<>();
		for (int i = 1; i <= columns; i++) {
			table.put(
				rsmd.getColumnName(i).toLowerCase(),
				rs.getString(i)
			);
		}
		return table;
	}
}
