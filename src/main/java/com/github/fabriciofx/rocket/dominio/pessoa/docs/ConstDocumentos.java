package com.github.fabriciofx.rocket.dominio.pessoa.docs;

import java.io.IOException;

import com.github.fabriciofx.rocket.doc.Cpf;
import com.github.fabriciofx.rocket.doc.Rg;
import com.github.fabriciofx.rocket.doc.Sexo;
import com.github.fabriciofx.rocket.doc.Tratamento;
import com.github.fabriciofx.rocket.doc.endereco.Endereco;
import com.github.fabriciofx.rocket.media.Media;

public final class ConstDocumentos implements Documentos {
	private final transient Cpf cpf;
	private final transient Rg rg;
	private final transient Sexo sexo;
	private final transient Tratamento tratamento;
	private final transient Endereco endereco;

	public ConstDocumentos(final Cpf cpf, final Rg rg, final Sexo sexo,
			final Tratamento tratamento, final Endereco endereco) {
				this.cpf = cpf;
				this.rg = rg;
				this.sexo = sexo;
				this.tratamento = tratamento;
				this.endereco = endereco;
	}
	
	@Override
	public Media print(final Media media) throws IOException {
		return endereco.print(
			tratamento.print(
				sexo.print(
					rg.print(
						cpf.print(media)
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
}
