package com.github.fabriciofx.rocket.doc;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.media.Media;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class Email implements Documento {
	private final String endereco;

	public Email(final String endereco) {
		this.endereco = endereco; 
	}

	@Override
	public String toString() {
		return new com.github.fabriciofx.rocket.constraint.Email<String>(
			new NotEmpty<>(
				new NotNull<>()
			)
		).valid(endereco);
	}

	@Override
	public Media print(final Media media) {
		return media.with("email", toString());
	}
}
