package com.github.fabriciofx.rocket.infra.media;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SqlMedia implements Media {
	private final transient String table;
	private final transient List<String> fields;
	private final transient List<String> values;

	public SqlMedia(final String table) {
		this(table, Collections.emptyList(), Collections.emptyList());
	}

	public SqlMedia(final String table, final List<String> fields,
			final List<String> values) {
		this.table = table;
		this.fields = new ArrayList<>(fields);
		this.values = new ArrayList<>(values);
	}

	@Override
	public Media with(String name, String value) {
		fields.add(name);
		values.add(String.format("'%s'", value));
		return new SqlMedia(table, fields, values);
	}

	@Override
	public String toString() {
		return insert();
	}

	public String insert() {
		return String.format("INSERT INTO %s (%s) VALUES (%s)", table,
				String.join(", ", fields), String.join(", ", values));
	}

	public String update() {
		return "";
	}
}
