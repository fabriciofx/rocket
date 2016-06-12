package com.github.fabriciofx.rocket.repository;

import java.util.List;

public interface Paginavel<T> {
	public long total();

	public List<T> itens(final long de, final long quantidade);
}