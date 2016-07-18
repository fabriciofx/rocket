package com.github.fabriciofx.rocket.repository;

import java.util.List;

public interface Paginable<T> {
	public long total();

	public List<T> itens(final long from, final long quantity);
}
