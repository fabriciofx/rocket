package com.github.fabriciofx.rocket.db;

public final class Usuario {
	private final transient String nome;
	private final transient String senha;

	public Usuario(final String nome, final String senha) {
		if (nome == null || nome.isEmpty()) {
			throw new IllegalArgumentException("nome de usuário inválido");
		}
		if (senha == null) {
			throw new IllegalArgumentException("senha de usuário inválida");
		}
		this.nome = nome;
		this.senha = senha;
	}

	public String nome() {
		return nome;
	}

	public String senha() {
		return senha;
	}
}
