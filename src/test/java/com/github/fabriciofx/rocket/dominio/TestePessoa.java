package com.github.fabriciofx.rocket.dominio;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.fabriciofx.rocket.dominio.endereco.Endereco;
import com.github.fabriciofx.rocket.dominio.repositorio.DataId;

public final class TestePessoa {
	@Test
	public void umaPessoaQualquer() {
		final String saida = "Pessoa {id=2015123101325444663, nome=José de Alencar, endereco=Endereco {}, fones=[999918967 (TIM CELULAR)]}";
		final Pessoa p = new Pessoa(new DataId(2015123101325444663L),
				new Nome("José de Alencar"), new Endereco(),
				new Fone("999918967", Fone.Tipo.CELULAR, Fone.Operadora.TIM));

		assertEquals(saida, p.toString());
	}
}
