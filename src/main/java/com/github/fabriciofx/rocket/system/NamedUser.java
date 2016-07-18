package com.github.fabriciofx.rocket.system;

import java.io.IOException;

import com.github.fabriciofx.rocket.dominio.documento.Email;
import com.github.fabriciofx.rocket.dominio.documento.Nome;
import com.github.fabriciofx.rocket.security.Password;

public interface NamedUser extends User {
	Nome name() throws IOException;

	NamedUser ANONYMOUS = new NamedUser() {
		@Override
		public Nome name() throws IOException {
			return new Nome("ANONYMOUS");
		}

		@Override
		public Email email() throws IOException {
			return User.ANONYMOUS.email();
		}

		@Override
		public Password password() throws IOException {
			return User.ANONYMOUS.password();
		}
	};
}
