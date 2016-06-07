package com.github.fabriciofx.rocket.infra.media;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.jcabi.immutable.Array;

public final class SqlMedia implements Media {
	private final transient String table;
	private final transient Array<String> fields;

	public SqlMedia(final String table, final String... fields) {
		this(table, Arrays.asList(fields));
	}

	public SqlMedia(final String table, final List<String> fields) {
		this.table = table;
		this.fields = new Array<>(fields);
	}

	@Override
	public Media with(String name, String value) {
		return new SqlMedia(table, fields.with(name));
	}
	
	@Override
	public String toString() {
		return insert();
	}
	
	public String insert() {
		return String.format("INSERT INTO %s (%s) VALUES (%s)", table,
			String.join(", ", fields),
			String.join(", ", Collections.nCopies(fields.size(), "?"))
		);		
	}
	
	public String update() {
		return "";
	}
}
