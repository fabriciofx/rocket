package com.github.fabriciofx.rocket.dominio;

import com.github.fabriciofx.rocket.restricao.RestNaoNulo;

public final class PessoaJuridica {
	private final Pessoa pessoa;
	private final Cnpj cnpj;

	public PessoaJuridica(final Pessoa pessoa, final Cnpj cnpj) {
		this.pessoa = new RestNaoNulo<Pessoa>(pessoa).objeto();
		this.cnpj = new RestNaoNulo<Cnpj>(cnpj).objeto();
	}

	public Pessoa comoPessoa() {
		return pessoa;
	}

	public Cnpj cnpj() {
		return cnpj;
	}

	@Override
	public String toString() {
		return String.format("PessoaJuridica {%s, CNPJ=%s}", pessoa, cnpj);
	}
}
