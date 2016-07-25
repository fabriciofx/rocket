package com.github.fabriciofx.rocket.system;

import java.io.IOException;

import com.github.fabriciofx.rocket.dominio.Nome;

public interface NamedUser extends User<Nome> {
	NamedUser ANONYMOUS = new NamedUser() {
		@Override
		public Nome name() throws IOException {
			return new Nome("Anonymous");
		}

		@Override
		public Password password() throws IOException {
			return new Password("Anonymous");
		}
	};
}
