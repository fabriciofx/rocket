package com.github.fabriciofx.rocket.dominio;

import com.github.fabriciofx.rocket.restricao.RestNaoNulo;

public final class PessoaFisica {
	private final Pessoa pessoa;
	private final Rg rg;

	public PessoaFisica(final Pessoa pessoa, final Rg rg) {
		this.pessoa = new RestNaoNulo<Pessoa>(pessoa).objeto();
		this.rg = new RestNaoNulo<Rg>(rg).objeto();
	}

	public Pessoa comoPessoa() {
		return pessoa;
	}

	public Rg RG() {
		return rg;
	}

	@Override
	public String toString() {
		return String.format("PessoaFisica {%s, RG=%s}", pessoa, rg);
	}
}
