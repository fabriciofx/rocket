package com.github.fabriciofx.rocket.dominio.endereco;

import java.io.IOException;

import com.github.fabriciofx.rocket.dominio.endereco.doc.Bairro;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Cep;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Cidade;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Complemento;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Logradouro;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Numero;
import com.github.fabriciofx.rocket.media.Media;

public final class ConstEndereco implements Endereco {
	private final transient Logradouro logradouro;
	private final transient Numero numero;
	private final transient Complemento complemento;
	private final transient Bairro bairro;
	private final transient Cidade cidade;
	private final transient Cep cep;

	public ConstEndereco(final Logradouro logradouro, final Numero numero,
			final Complemento complemento, final Bairro bairro,
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
	public Logradouro logradouro() throws IOException {
		return logradouro;
	}

	@Override
	public Numero numero() throws IOException {
		return numero;
	}

	@Override
	public Complemento complemento() throws IOException {
		return complemento;
	}

	@Override
	public Bairro bairro() throws IOException {
		return bairro;
	}

	@Override
	public Cidade cidade() throws IOException {
		return cidade;
	}

	@Override
	public Cep cep() throws IOException {
		return cep;
	}
}
