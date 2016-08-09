package com.github.fabriciofx.rocket.dominio.fone;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.github.fabriciofx.rocket.dominio.fone.Fone.Operadora;
import com.github.fabriciofx.rocket.dominio.fone.Fone.Tipo;
import com.github.fabriciofx.rocket.media.Media;

public final class ConstFones implements Fones {
	private final transient List<Fone> fones;
	
	public ConstFones(final Fone... fones) {
		this(Arrays.asList(fones));
	}
	
	public ConstFones(final List<Fone> fones) {
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
	public List<Fone> todos() throws IOException {
		return Collections.unmodifiableList(fones);
	}

	@Override
	public void adiciona(final String numero, final Tipo tipo,
			final Operadora operadora) throws IOException {
		throw new UnsupportedOperationException("#adiciona()"); 
	}
}