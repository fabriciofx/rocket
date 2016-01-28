package com.github.fabriciofx.rocket.dominio;

import java.util.Objects;

public final class Fone {
	public enum Operadora {
		TIM, OI, CLARO, AEIOU, GVT, EMBRATEL, TELEFONICA, NEXTEL, VIVO, DESCONHECIDO;
	}

	public enum Tipo {
		CELULAR, FIXO, RADIO, DESCONHECIDO;
	}

	private final String numero;
	private final Tipo tipo;
	private final Operadora operadora;

	public Fone(final String numero, final Tipo tipo,
			final Operadora operadora) {
		Objects.requireNonNull(numero,
				"argumento 'numero' de telefone não pode ser NULL");

		if (!numero.matches("[0-9]+")) {
			throw new IllegalArgumentException(
					"argumento 'numero' de telefone só pode possuir números");
		}

		this.numero = numero;
		this.tipo = Objects.requireNonNull(tipo,
				"argumento 'tipo' de telefone não pode ser NULL");
		this.operadora = Objects.requireNonNull(operadora,
				"argumento 'operadora' de telefone não pode ser NULL");
	}

	public String numero() {
		return numero;
	}

	public Tipo tipo() {
		return tipo;
	}

	public Operadora operadora() {
		return operadora;
	}

	@Override
	public boolean equals(final Object o) {
		return o != null && o instanceof Fone
				&& numero.equals(Fone.class.cast(o).numero);
	}

	@Override
	public int hashCode() {
		return 31 + ((numero == null) ? 0 : numero.hashCode());
	}

	@Override
	public String toString() {
		return String.format("%s (%s %s)", numero, operadora, tipo);
	}
}
