package com.github.fabriciofx.rocket.infra.sistema;

import java.io.IOException;

import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.dominio.Email;
import com.github.fabriciofx.rocket.dominio.Nome;
import com.github.fabriciofx.rocket.dominio.Senha;

public interface Usuario {
	Nome nome() throws IOException;

	Email email() throws IOException;

	Senha senha() throws IOException;
	
	Usuario ANONIMO = new Usuario() {
		@Override
		public Nome nome() throws IOException {
			return new Nome("ANONIMO");
		}

		@Override
		public Email email() throws IOException {
			return new Email("anonimo@anonimo.com");
		}

		@Override
		public Senha senha() throws IOException {
			return new Senha("anonimo");
		}		
	};

	public final class Padrao implements Usuario {
		private final transient Nome nome;
		private final transient Email email;
		private final transient Senha senha;

		public Padrao(final Nome nome, final Email email, final Senha senha) {
			this.nome = new NotNull<Nome>().valid(nome);
			this.email = new NotNull<Email>().valid(email);
			this.senha = new NotNull<Senha>().valid(senha);
		}

		@Override
		public Nome nome() throws IOException {
			return nome;
		}

		@Override
		public Email email() throws IOException {
			return email;
		}

		@Override
		public Senha senha() throws IOException {
			return senha;
		}
	}
}
