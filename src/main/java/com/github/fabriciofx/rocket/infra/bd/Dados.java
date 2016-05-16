package com.github.fabriciofx.rocket.infra.bd;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class Dados {
	private final transient List<Map<String, Object>> tabela;

	public Dados(final List<Map<String, Object>> tabela) {
		this.tabela = tabela;
	}

	public Object item(final int numero, final String nome) {
		return tabela.get(numero).get(nome);
	}

	public List<Map<String, Object>> itens() {
		return tabela;
	}
	
	public List<Object> itens(final String nome) {
		final List<Object> itens = new LinkedList<>();
		for (int n = 0; n < tabela.size(); n++) {
			itens.add(item(n, nome));
		}
		return itens;
	}
}
