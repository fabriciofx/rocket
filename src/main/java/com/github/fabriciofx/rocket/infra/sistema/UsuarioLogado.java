package com.github.fabriciofx.rocket.infra.sistema;

import java.io.IOException;

import com.github.fabriciofx.rocket.dominio.Email;
import com.github.fabriciofx.rocket.dominio.Nome;
import com.github.fabriciofx.rocket.dominio.Senha;

public interface UsuarioLogado extends Usuario {
	boolean logado();
	
	public class Simples implements UsuarioLogado {
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
		public boolean logado() {
			return logado;
		}		
	}
}
