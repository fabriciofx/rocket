package com.github.fabriciofx.rocket.dominio;

import java.util.Objects;

public final class PessoaJuridica {
	private final Pessoa pessoa;
	private final Cnpj cnpj;

	public PessoaJuridica(final Pessoa pessoa, final Cnpj cnpj) {
		this.pessoa = Objects.requireNonNull(pessoa,
				"pessoa de pessoa jurídica inválida (null)");
		this.cnpj = Objects.requireNonNull(cnpj,
				"CNPJ de pessoa jurídica inválido (null)");
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
