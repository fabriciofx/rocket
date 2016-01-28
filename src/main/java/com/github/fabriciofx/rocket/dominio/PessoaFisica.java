package com.github.fabriciofx.rocket.dominio;

import java.util.Objects;

public final class PessoaFisica {
	private final Pessoa pessoa;
	private final Rg rg;

	public PessoaFisica(final Pessoa pessoa, final Rg rg) {
		this.pessoa = Objects.requireNonNull(pessoa,
				"pessoa não pode ser NULL");
		this.rg = Objects.requireNonNull(rg, "RG não pode ser NULL");
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
