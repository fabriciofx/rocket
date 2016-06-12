package com.github.fabriciofx.rocket.repository;

import java.util.Objects;

public final class Paginas<T> {
	private final transient Paginavel<T> paginavel;
	private final transient long quantidade;

	public Paginas(final Paginavel<T> paginavel, final long quantidade) {
		this.paginavel = Objects.requireNonNull(paginavel,
				"argumento 'paginavel' não pode ser NULL");
		this.quantidade = quantidade;
	}

	public long total() {
		final long total = paginavel.total();

		return total / quantidade + (total % quantidade == 0L ? 0L : 1L);
	}

	public Pagina<T> primeira() {
		return new Pagina<T>(paginavel.itens(0L, quantidade), 1L);
	}

	public Pagina<T> ultima() {
		final long total = total();
		final long de = total <= 0L ? 0L : (total - 1L) * quantidade;

		return new Pagina<T>(paginavel.itens(de, quantidade), total);
	}

	public Pagina<T> pagina(final long numero) {
		if (numero < 1L) {
			return primeira();
		}

		if (numero > total()) {
			return ultima();
		}

		final long de = (numero - 1L) * quantidade;
		return new Pagina<T>(paginavel.itens(de, quantidade), numero);
	}
}
