package com.github.fabriciofx.rocket.system;

import java.io.IOException;

import com.github.fabriciofx.rocket.dominio.Email;
import com.github.fabriciofx.rocket.dominio.Nome;
import com.github.fabriciofx.rocket.dominio.Password;

public interface Logged extends User {
	boolean logged() throws IOException;

	Logged USER = new Logged.Simple(User.ANONYMOUS, false);

	public class Simple implements Logged {
		private final transient User origem;
		private final transient boolean logged;

		public Simple(final User origem, final boolean logado) {
			this.origem = origem;
			this.logged = logado;
		}

		@Override
		public Nome name() throws IOException {
			return origem.name();
		}

		@Override
		public Email email() throws IOException {
			return origem.email();
		}

		@Override
		public Password password() throws IOException {
			return origem.password();
		}

		@Override
		public boolean logged() throws IOException {
			return logged;
		}
	}
}
