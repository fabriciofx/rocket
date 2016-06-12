package com.github.fabriciofx.rocket.dominio.simples;

import java.io.IOException;

import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.dominio.endereco.Bairro;
import com.github.fabriciofx.rocket.dominio.endereco.Cep;
import com.github.fabriciofx.rocket.dominio.endereco.Cidade;
import com.github.fabriciofx.rocket.dominio.endereco.Complemento;
import com.github.fabriciofx.rocket.dominio.endereco.Endereco;
import com.github.fabriciofx.rocket.dominio.endereco.Logradouro;
import com.github.fabriciofx.rocket.dominio.endereco.Numero;
import com.github.fabriciofx.rocket.media.Media;

public final class SimplesEndereco implements Endereco {
	private final transient Logradouro logradouro;
	private final transient Numero numero;
	private final transient Complemento complemento;
	private final transient Bairro bairro;
	private final transient Cidade cidade;
	private final transient Cep cep;
	
	public SimplesEndereco(final Logradouro logradouro, final Numero numero,
			final Complemento complemento, final Bairro bairro,
			final Cidade cidade, final Cep cep) {
		this.logradouro	= new NotNull<Logradouro>().valid(logradouro);
		this.numero = new NotNull<Numero>().valid(numero);
		this.complemento = new NotNull<Complemento>().valid(complemento);
		this.bairro = new NotNull<Bairro>().valid(bairro);
		this.cidade = new NotNull<Cidade>().valid(cidade);
		this.cep = new NotNull<Cep>().valid(cep);
	}
	
	@Override
	public Media print(Media media) throws IOException {
		media = logradouro.print(media);
		media = numero.print(media);
		media = complemento.print(media);
		media = bairro.print(media);
		media = cidade.print(media);
		media = cep.print(media);
		return media;
	}

	@Override
	public Logradouro logradouro() {
		return logradouro;
	}

	@Override
	public void logradouro(Logradouro logradouro) {
	}

	@Override
	public Numero numero() {
		return numero;
	}

	@Override
	public void numero(Numero numero) {
	}

	@Override
	public Complemento complemento() {
		return complemento;
	}

	@Override
	public void complemento(Complemento complemento) {
	}

	@Override
	public Bairro bairro() {
		return bairro;
	}

	@Override
	public void bairro(Bairro bairro) {
	}

	@Override
	public Cidade cidade() {
		return cidade;
	}

	@Override
	public void cidade(Cidade cidade) {
	}

	@Override
	public Cep cep() {
		return cep;
	}

	@Override
	public void cep(Cep cep) {
	}
}
