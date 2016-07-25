package com.github.fabriciofx.rocket.system;

import java.io.IOException;

import com.github.fabriciofx.rocket.dominio.Nome;
import com.github.fabriciofx.rocket.dominio.doc.Email;

public interface User<T> {
	T name() throws IOException;

	Password password() throws IOException;
	
	User<Nome> NAMED_ANONYMOUS = new User<Nome>() {
		@Override
		public Nome name() throws IOException {
			return new Nome("Anonymous");
		}

		@Override
		public Password password() throws IOException {
			return new Password("Anonymous");
		}
	};
	
	User<Email> EMAILED_ANONYMOUS = new User<Email>() {
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
