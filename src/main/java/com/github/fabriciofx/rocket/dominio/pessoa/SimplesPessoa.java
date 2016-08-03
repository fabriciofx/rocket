package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.IOException;

import com.github.fabriciofx.rocket.dominio.Nome;
import com.github.fabriciofx.rocket.dominio.pessoa.doc.Documentos;
import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.id.UuidId;
import com.github.fabriciofx.rocket.media.Media;

public final class SimplesPessoa implements Pessoa {
	private final transient Id id;
	private final transient Nome nome;
	private final transient Documentos documentos;

	public SimplesPessoa(final Nome nome, final Documentos documentos) {
		this(new UuidId(), nome, documentos);
	}
	
	public SimplesPessoa(final Id id, final Nome nome,
			final Documentos documentos) {
		this.id = id;
		this.nome = nome;
		this.documentos = documentos;
	}
	
	@Override
	public Id id() {
		return id;
	}

	@Override
	public Media print(final Media media) throws IOException {
		return documentos.print(
			media.with("id", id)
		);
	}

	@Override
	public Nome nome() throws IOException {
		return nome;
	}

	@Override
	public Documentos documentos() throws IOException {
		return documentos;
	}
}
