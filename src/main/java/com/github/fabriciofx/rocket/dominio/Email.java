package com.github.fabriciofx.rocket.dominio;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.infra.media.Media;

public final class Email implements Documento {
	private final transient String endereco;

	public Email(final String endereco) {
		this.endereco = new com.github.fabriciofx.rocket.constraint.Email<String>(
				new NotEmpty<>(new NotNull<>())).valid(endereco);
	}

	@Override
	public boolean equals(final Object o) {
		return o != null && o instanceof Email
				&& endereco.equals(Email.class.cast(o).endereco);
	}

	@Override
	public int hashCode() {
		return 31 + ((endereco == null) ? 0 : endereco.hashCode());
	}

	@Override
	public String toString() {
		return endereco;
	}

	@Override
	public Media print(Media media) {
		return media.with("email", endereco);
	}
}
