package com.github.fabriciofx.rocket.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Page<T> {
	private final transient List<T> itens;
	private final transient long num;

	public Page(final List<T> itens, final long num) {
		this.itens = new ArrayList<>(itens);
		this.num = num;
	}

	public List<T> itens() {
		return Collections.unmodifiableList(itens);
	}

	public long num() {
		return num;
	}
}
