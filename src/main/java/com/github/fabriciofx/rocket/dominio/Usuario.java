package com.github.fabriciofx.rocket.dominio;

import java.io.IOException;

import com.github.fabriciofx.rocket.restricao.RestNaoNulo;

public interface Usuario {
	Nome nome() throws IOException;

	Email email() throws IOException;

	Senha senha() throws IOException;

	public final class Padrao implements Usuario {
		private final transient Nome nome;
		private final transient Email email;
		private final transient Senha senha;

		public Padrao(final Nome nome, final Email email, final Senha senha) {
			this.nome = new RestNaoNulo<Nome>().valido(nome);
			this.email = new RestNaoNulo<Email>().valido(email);
			this.senha = new RestNaoNulo<Senha>().valido(senha);
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
