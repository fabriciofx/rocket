package com.github.fabriciofx.rocket.system;

import java.io.IOException;

import com.github.fabriciofx.rocket.dominio.Email;
import com.github.fabriciofx.rocket.dominio.Nome;
import com.github.fabriciofx.rocket.dominio.Senha;

public interface Logado extends Usuario {
	boolean logado() throws IOException;

	Logado USUARIO = new Logado.Simples(Usuario.ANONIMO, false);

	public class Simples implements Logado {
		private final transient Usuario origem;
		private final transient boolean logado;

		public Simples(final Usuario origem, final boolean logado) {
			this.origem = origem;
			this.logado = logado;
		}

		@Override
		public Nome nome() throws IOException {
			return origem.nome();
		}

		@Override
		public Email email() throws IOException {
			return origem.email();
		}

		@Override
		public Senha senha() throws IOException {
			return origem.senha();
		}

		@Override
		public boolean logado() throws IOException {
			return logado;
		}
	}
}
