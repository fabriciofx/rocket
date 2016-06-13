package com.github.fabriciofx.rocket.db;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class Data {
	private final transient List<Map<String, Object>> table;

	public Data(final List<Map<String, Object>> table) {
		this.table = table;
	}

	public Object item(final int number, final String name) {
		return table.get(number).get(name);
	}

	public List<Map<String, Object>> itens() {
		return table;
	}

	public List<Object> itens(final String name) {
		final List<Object> itens = new LinkedList<>();
		for (int n = 0; n < table.size(); n++) {
			itens.add(item(n, name));
		}
		return itens;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		// Build the header
		for (final String c : table.get(0).keySet()) {
			sb.append(c).append(" ");
		}
		sb.append("\n");
		// Build the lines
		for (int i = 1; i < table.size(); i++) {
			for (final Object o : table.get(i).values()) {
				sb.append(o.toString()).append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
