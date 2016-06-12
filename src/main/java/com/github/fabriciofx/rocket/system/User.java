package com.github.fabriciofx.rocket.system;

import java.io.IOException;

import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.dominio.Email;
import com.github.fabriciofx.rocket.dominio.Nome;
import com.github.fabriciofx.rocket.dominio.Password;

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

	public final class Default implements User {
		private final transient Nome name;
		private final transient Email email;
		private final transient Password senha;

		public Default(final Nome name, final Email email,
				final Password password) {
			this.name = new NotNull<Nome>().valid(name);
			this.email = new NotNull<Email>().valid(email);
			this.senha = new NotNull<Password>().valid(password);
		}

		@Override
		public Nome name() throws IOException {
			return name;
		}

		@Override
		public Email email() throws IOException {
			return email;
		}

		@Override
		public Password password() throws IOException {
			return senha;
		}
	}
}
