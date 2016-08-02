package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.IOException;

import com.github.fabriciofx.rocket.dominio.endereco.Endereco;
import com.github.fabriciofx.rocket.dominio.fone.Fones;
import com.github.fabriciofx.rocket.dominio.pessoa.doc.Cpf;
import com.github.fabriciofx.rocket.dominio.pessoa.doc.Rg;
import com.github.fabriciofx.rocket.dominio.pessoa.doc.Sexo;
import com.github.fabriciofx.rocket.dominio.pessoa.doc.Tratamento;
import com.github.fabriciofx.rocket.media.Media;

public final class MeDocumentos implements Documentos {
	private final transient Cpf cpf;
	private final transient Rg rg;
	private final transient Sexo sexo;
	private final transient Tratamento tratamento;
	private final transient Endereco endereco;
	private final transient Fones fones;

	public MeDocumentos(final Cpf cpf, final Rg rg, final Sexo sexo,
			final Tratamento tratamento, final Endereco endereco,
			final Fones fones) {
				this.cpf = cpf;
				this.rg = rg;
				this.sexo = sexo;
				this.tratamento = tratamento;
				this.endereco = endereco;
				this.fones = fones;
	}
	
	@Override
	public Media print(final Media media) throws IOException {
		return fones.print(
			endereco.print(
				tratamento.print(
					sexo.print(
						rg.print(
							cpf.print(media)
						)
					)
				)
			)
		);
	}

	@Override
	public Cpf cpf() throws IOException {
		return cpf;
	}

	@Override
	public Rg rg() throws IOException {
		return rg;
	}

	@Override
	public Sexo sexo() throws IOException {
		return sexo;
	}

	@Override
	public Tratamento tratamento() throws IOException {
		return tratamento;
	}

	@Override
	public Endereco endereco() throws IOException {
		return endereco;
	}

	@Override
	public Fones fones() throws IOException {
		return fones;
	}
}
