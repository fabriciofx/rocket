package com.github.fabriciofx.rocket.media;

import java.util.ArrayList;
import java.util.List;

public final class InsertSqlMedia implements Media {
	private final transient String table;
	private final transient List<String> fields;
	private final transient List<String> values;

	public InsertSqlMedia(final String table) {
		this(table, new ArrayList<>(), new ArrayList<>());
	}

	public InsertSqlMedia(final String table, final List<String> fields,
			final List<String> values) {
		this.table = table;
		this.fields = fields;
		this.values = values;
	}

	@Override
	public Media with(final String name, final String value) {
		fields.add(name);
		values.add(String.format("\'%s\'", value));
		return new InsertSqlMedia(table, new ArrayList<>(fields),
				new ArrayList<>(values));
	}
	
	@Override
	public Media dup() {
		return new InsertSqlMedia(table);
	}

	@Override
	public String toString() {
		return String.format("INSERT INTO %s (%s) VALUES (%s)", table,
				String.join(", ", fields), String.join(", ", values));
	}
}
