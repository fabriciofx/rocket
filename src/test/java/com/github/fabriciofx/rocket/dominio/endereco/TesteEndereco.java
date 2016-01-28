package com.github.fabriciofx.rocket.dominio.endereco;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.fabriciofx.rocket.dominio.endereco.Cep;
import com.github.fabriciofx.rocket.dominio.endereco.Endereco;
import com.github.fabriciofx.rocket.dominio.endereco.FmtEndereco;

public class TesteEndereco {
	@Test
	public void criaUmEndereco() {
		/*
		 * Av Gov Torquato Nepomuceno Neves, 123
		 * AP 101
		 * Vila Madalena
		 * 48035-120   São Paulo-SP
		 */
		final Endereco endereco = new Endereco()
				.comAtributo("logradouro", "Av Gov Torquato Nepomuceno Neves")
				.comAtributo("numero", 123)
				.comAtributo("complemento", "AP 101")
				.comAtributo("bairro", "Vila Madalena")
				.comAtributo("cep", new Cep("48035120"))
				.comAtributo("cidade", "São Paulo")
				.comAtributo("estado", "SP");

		final String saida = "Av Gov Torquato Nepomuceno Neves, 123\n"
				+ "AP 101\n" + "Vila Madalena\n" + "48035-120   São Paulo-SP";
		
		final FmtEndereco formatado = new FmtEndereco(endereco);
		assertEquals(saida, formatado.toString());
	}
}
