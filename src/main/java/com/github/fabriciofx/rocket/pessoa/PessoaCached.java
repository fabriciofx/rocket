package com.github.fabriciofx.rocket.pessoa;

import java.io.IOException;
import java.util.Map;

import com.github.fabriciofx.rocket.fone.Fones;
import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.media.Media;
import com.github.fabriciofx.rocket.media.XmlMedia;

public final class PessoaCached implements Pessoa {
	private final Pessoa origem;
	private final Media about;

	public PessoaCached(final Pessoa origem) throws IOException {
		this(origem, new XmlMedia("pessoa"));
	}
	
	public PessoaCached(final Pessoa origem, final Media media)
		throws IOException {
		this.origem = origem;
		this.about = origem.about(media);
	}
	
	@Override
	public Id id() {
		return origem.id();
	}
	
	@Override
	public Media about(final Media media) throws IOException {
		return this.about;
	}

	@Override
	public void renomeia(final String nome) throws IOException {
		throw new UnsupportedOperationException("cached pessoa doesn't change");
	}

	@Override
	public void documentos(final Map<String, String> documentos)
		throws IOException {
		throw new UnsupportedOperationException("cached pessoa doesn't change");
	}

	@Override
	public Fones fones() throws IOException {
		return null;
	}
}
