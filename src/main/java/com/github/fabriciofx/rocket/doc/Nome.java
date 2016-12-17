package com.github.fabriciofx.rocket.doc;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.constraint.Pattern;
import com.github.fabriciofx.rocket.media.Media;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class Nome implements Documento {
	private final String completo;

	public Nome(final String completo) {
		this.completo = completo; 
	}

	@Override
	public final String toString() {
		return new Pattern<String>(
			new NotEmpty<>(
				new NotNull<>()
			),
			"^[\\p{L} \\.\\'-]+$"
		).valid(completo).trim().replaceAll("\\s+", " ");
	}

	@Override
	public Media print(final Media media) {
		return media.with("nome", toString());
	}
}
