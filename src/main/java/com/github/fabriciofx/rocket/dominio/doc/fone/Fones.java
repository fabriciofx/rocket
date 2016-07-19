package com.github.fabriciofx.rocket.dominio.doc.fone;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.fabriciofx.rocket.dominio.doc.Documento;
import com.github.fabriciofx.rocket.media.Media;

public final class Fones implements Documento {
	private final transient List<Fone> fones;
	
	public Fones(final Fone... fones) {
		this(Arrays.asList(fones));
	}
	
	public Fones(final List<Fone> fones) {
		this.fones = new ArrayList<>(fones);
	}

	@Override
	public Media print(final Media media) throws IOException {
		Media m = media;
		for (final Fone f : fones) {
			m = f.print(m);
		}
		return m;
 	}
}
