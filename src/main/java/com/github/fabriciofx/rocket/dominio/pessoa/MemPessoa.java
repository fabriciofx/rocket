package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.IOException;

import com.github.fabriciofx.rocket.doc.Cpf;
import com.github.fabriciofx.rocket.doc.Nome;
import com.github.fabriciofx.rocket.doc.Rg;
import com.github.fabriciofx.rocket.doc.endereco.Endereco;
import com.github.fabriciofx.rocket.doc.fone.Fones;
import com.github.fabriciofx.rocket.media.Media;

public final class MemPessoa implements Pessoa {
	private final Nome nome;
	private final Cpf cpf;
	private final Rg rg;
	private final Endereco endereco;
	private final Fones fones;

	public MemPessoa(final Nome nome, final Cpf cpf, final Rg rg,
			final Endereco endereco, final Fones fones) {
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.endereco = endereco;
		this.fones = fones;
	}

	@Override
	public Media print(final Media media) throws IOException {
		return nome.print(
			cpf.print(
				rg.print(
					endereco.print(
						fones.print(media)
					)
				)
			)
		);
	}

	@Override
	public Nome nome() throws IOException {
		return nome;
	}

	@Override
	public Fones fones() throws IOException {
		return fones;
	}

	@Override
	public void atualize(final Nome nome, final Cpf cpf, final Rg rg,
		final Endereco endereco) throws IOException {
	}
}
