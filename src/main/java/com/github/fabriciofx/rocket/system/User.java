package com.github.fabriciofx.rocket.system;

import java.io.IOException;

import com.github.fabriciofx.rocket.dominio.documento.Email;
import com.github.fabriciofx.rocket.dominio.documento.Nome;
import com.github.fabriciofx.rocket.security.Password;

public interface User {
	Nome name() throws IOException;

	Email email() throws IOException;

	Password password() throws IOException;
	
	User ANONYMOUS = new User() {
		@Override
		public Nome name() throws IOException {
			return new Nome("ANONYMOUS");
		}

		@Override
		public Email email() throws IOException {
			return new Email("anonymous@anonymous.com");
		}

		@Override
		public Password password() throws IOException {
			return new Password("anonymous");
		}		
	};
}
