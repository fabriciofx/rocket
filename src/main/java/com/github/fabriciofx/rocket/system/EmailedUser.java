package com.github.fabriciofx.rocket.system;

import java.io.IOException;

import com.github.fabriciofx.rocket.dominio.pessoa.doc.Email;

public interface EmailedUser extends User<Email> {
	EmailedUser ANONYMOUS = new EmailedUser() {
		@Override
		public Email name() throws IOException {
			return new Email("anonymous@anonymous.com");
		}

		@Override
		public Password password() throws IOException {
			return new Password("Anonymous");
		}
	};
}
