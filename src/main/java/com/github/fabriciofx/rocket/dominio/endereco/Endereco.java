package com.github.fabriciofx.rocket.dominio.endereco;

import java.util.Set;

import com.jcabi.immutable.ArrayMap;

public final class Endereco {
	private final ArrayMap<String, Object> atributos;

	public Endereco() {
		this(new ArrayMap<>());
	}

	public Endereco(final ArrayMap<String, Object> atributos) {
		this.atributos = atributos;
	}

	public Endereco comAtributo(final String nome, final Object valor) {
		return new Endereco(atributos.with(nome, valor));
	}

	public Set<String> atributos() {
		return atributos.keySet();
	}

	public Object atributo(final String nome) {
		final Object atributo = atributos.get(nome);

		return atributo == null ? "" : atributo;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();

		for (final String chave : atributos()) {
			sb.append(chave).append("=").append(atributo(chave).toString())
					.append(", ");
		}

		return String.format("%s {%s}", this.getClass().getSimpleName(),
				sb.toString());
	}
}
