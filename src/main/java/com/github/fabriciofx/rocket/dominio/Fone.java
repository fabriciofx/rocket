package com.github.fabriciofx.rocket.dominio;

import com.github.fabriciofx.rocket.restricao.RestNaoNulo;
import com.github.fabriciofx.rocket.restricao.RestNaoVazia;
import com.github.fabriciofx.rocket.restricao.RestPadrao;

public final class Fone {
	public enum Operadora {
		TIM, OI, CLARO, AEIOU, GVT, EMBRATEL, TELEFONICA, NEXTEL, VIVO,
		DESCONHECIDO;
	}

	public enum Tipo {
		CELULAR, FIXO, RADIO, DESCONHECIDO;
	}

	private final String numero;
	private final Tipo tipo;
	private final Operadora operadora;

	public Fone(final String numero, final Tipo tipo,
			final Operadora operadora) {
		this.numero = new RestPadrao<String>(new RestNaoVazia<>(
					new RestNaoNulo<>(numero)
					), "[0-9]+"
				).objeto();
		this.tipo = new RestNaoNulo<Tipo>(tipo).objeto();
		this.operadora = new RestNaoNulo<Operadora>(operadora).objeto();
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
