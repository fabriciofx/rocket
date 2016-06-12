package com.github.fabriciofx.rocket.dominio;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.constraint.Pattern;
import com.github.fabriciofx.rocket.infra.media.Media;

public final class Nome implements Documento {
	private final transient String completo;

	public Nome(final String completo) {
		this.completo = new Pattern<String>(
			new NotEmpty<>(
				new NotNull<>()
			),
			"^[\\p{L} .'-]+$"
		).valid(completo).trim().replaceAll("\\s+", " ");
	}

	public String completo() {
		return completo;
	}

	public String primeiro() {
		return completo.split("\\s")[0];
	}

	public String ultimo() {
		final String[] parts = completo.split("\\s");
		final int pos = parts.length - 1 > 0 ? parts.length - 1 : 0;

		return parts[pos];
	}

	public String primeiroUltimo() {
		return primeiro() + " " + ultimo();
	}

	public String ultimoPrimeiro() {
		return ultimo() + " " + primeiro();
	}

	public String americano() {
		return ultimo() + ", " + primeiro();
	}

	@Override
	public final boolean equals(final Object o) {
		return o != null && o instanceof Nome
				&& completo.equals(Nome.class.cast(o).completo);
	}

	@Override
	public final int hashCode() {
		return 31 + ((completo == null) ? 0 : completo.hashCode());
	}

	@Override
	public final String toString() {
		return completo;
	}

	@Override
	public Media print(Media media) {
		return media.with("nome", completo);
	}
}
