package com.github.fabriciofx.rocket.dominio.endereco;

import java.util.Objects;

public final class FmtEndereco {
	private final Endereco endereco;

	public FmtEndereco(final Endereco endereco) {
		this.endereco = Objects.requireNonNull(endereco,
				"endereço não pode ser NULL");
	}

	@Override
	public String toString() {
		return String.format("%s, %s\n%s\n%s\n%s   %s-%s",
				endereco.atributo("logradouro"), endereco.atributo("numero"),
				endereco.atributo("complemento"), endereco.atributo("bairro"),
				new FmtCep((Cep) endereco.atributo("cep")).toString(),
				endereco.atributo("cidade"), endereco.atributo("estado"));
	}
}
