package com.github.fabriciofx.rocket.dominio.gerador;

import com.github.fabriciofx.rocket.dominio.endereco.Cep;
import com.github.fabriciofx.rocket.dominio.endereco.Endereco;
import com.github.fabriciofx.rocket.misc.Aleatorio;

public final class Enderecos {
	private final static Endereco[] ENDERECOS = {
			new Endereco()
					.comAtributo("logradouro",
							"Av Governador Flávio Ribeiro Coutinho")
					.comAtributo("numero", "32196")
					.comAtributo("bairro", "Manaíra")
					.comAtributo("cidade", "João Pessoa")
					.comAtributo("estado", "PB")
					.comAtributo("cep", new Cep("58037000").toString()),
			new Endereco()
					.comAtributo("logradouro",
							"Av Governador Argemiro de Figueiredo")
					.comAtributo("numero", "123").comAtributo("bairro", "Bessa")
					.comAtributo("cidade", "João Pessoa")
					.comAtributo("estado", "PB")
					.comAtributo("cep", new Cep("58037005").toString()) };

	public Enderecos() {
	}

	public Endereco get() {
		final int n = new Aleatorio().numero(0, ENDERECOS.length - 1);

		return ENDERECOS[n];
	}

	public String getString() {
		return get().toString();
	}
}
