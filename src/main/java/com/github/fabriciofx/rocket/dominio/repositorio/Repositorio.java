package com.github.fabriciofx.rocket.dominio.repositorio;

import java.util.List;

import com.github.fabriciofx.rocket.repository.Pages;

public interface Repositorio<T extends Identificavel<?>> {
	public void adiciona(final T objeto);

	public T procura(final T objeto);

	public List<T> todos();

	public Pages<T> paginas(final int numItens);
}
