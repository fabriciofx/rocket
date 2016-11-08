package com.github.fabriciofx.rocket.doc.endereco;

import java.io.IOException;

import com.github.fabriciofx.rocket.media.Media;

public final class MemEndereco implements Endereco {
	private final String logradouro;
	private final String numero;
	private final String complemento;
	private final String bairro;
	private final Cidade cidade;
	private final Cep cep;

	public MemEndereco(final String logradouro, final String numero,
			final String complemento, final String bairro,
			final Cidade cidade, final Cep cep) {
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.cep = cep;
	}

	@Override
	public Media print(final Media media) throws IOException {
		return media
			.with("logradouro", logradouro)
			.with("numero", numero)
			.with("complemento", complemento)
			.with("bairro", bairro)
			.with("cidade", cidade)
			.with("cep", cep);
	}

	@Override
	public void apague() throws IOException {
		throw new UnsupportedOperationException(
			"não é possível apagar este endereço"
		);
	}

	@Override
	public void atualize(final String logradouro, final String numero,
			final String complemento, final String bairro,
			final Cidade cidade, final Cep cep) throws IOException {
		throw new UnsupportedOperationException(
			"não é possível atualizar este endereço"
		);
	}
}
