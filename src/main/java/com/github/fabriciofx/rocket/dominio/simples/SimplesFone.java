package com.github.fabriciofx.rocket.dominio.simples;

import com.github.fabriciofx.rocket.dominio.Fone;
import com.github.fabriciofx.rocket.infra.media.Media;
import com.github.fabriciofx.rocket.restricao.RestNaoNulo;
import com.github.fabriciofx.rocket.restricao.RestNaoVazia;
import com.github.fabriciofx.rocket.restricao.RestPadrao;

public final class SimplesFone implements Fone {
	private final transient String numero;
	private final transient Tipo tipo;
	private final transient Operadora operadora;

	public SimplesFone(final String numero, final Tipo tipo,
			final Operadora operadora) {
		this.numero = new RestPadrao<String>(
			new RestNaoVazia<>(
				new RestNaoNulo<>()
			),
			"[0-9]+"
		).valido(numero);
		this.tipo = new RestNaoNulo<Tipo>().valido(tipo);
		this.operadora = new RestNaoNulo<Operadora>().valido(operadora);
	}

	@Override
	public boolean equals(final Object o) {
		return o != null && o instanceof SimplesFone
				&& numero.equals(SimplesFone.class.cast(o).numero);
	}

	@Override
	public int hashCode() {
		return 31 + ((numero == null) ? 0 : numero.hashCode());
	}

	@Override
	public String toString() {
		return String.format("%s:%s:%s", numero, tipo, operadora);
	}

	@Override
	public Media print(Media media) {
		return media.with("numero", numero)
			.with("tipo", tipo.toString())
			.with("operadora", operadora.toString());
//		return media.with("fone-numero", numero)
//				.with("fone-tipo", tipo.toString())
//				.with("fone-operadora", operadora.toString());
	}

	@Override
	public String numero() {
		return numero;
	}

	@Override
	public void numero(String numero) {
	}

	@Override
	public Tipo tipo() {
		return tipo;
	}

	@Override
	public void tipo(Tipo tipo) {
	}

	@Override
	public Operadora operadora() {
		return operadora;
	}

	@Override
	public void operadora(Operadora operadora) {
	}
}
