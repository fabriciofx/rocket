package com.github.fabriciofx.rocket.dominio.me;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.github.fabriciofx.rocket.dominio.Fone;
import com.github.fabriciofx.rocket.dominio.Fones;
import com.github.fabriciofx.rocket.media.Media;

public final class MeFones implements Fones {
	private final transient List<Fone> fones;
	
	public MeFones(final Fone... fones) {
		this(Arrays.asList(fones));
	}
	
	public MeFones(final List<Fone> fones) {
		this.fones = new ArrayList<>(fones);
	}
	
	@Override
	public Media print(final Media media) throws IOException {
		Media m = media;
		for (final Fone f  : fones) {
			m = f.print(m);
		}
		return m;
	}

	@Override
	public Fone fone(final String numero) throws IOException {
		for (final Fone f : fones) {
			if (f.numero().equals(numero)) {
				return f;
			}
		}
		throw new IOException(
			String.format("número de telefone %s não encontrado", numero)
		); 
	}

	@Override
	public List<Fone> todos() throws IOException {
		return Collections.unmodifiableList(fones);
	}

	@Override
	public void salva(final Fones fones) throws IOException {
	}
}