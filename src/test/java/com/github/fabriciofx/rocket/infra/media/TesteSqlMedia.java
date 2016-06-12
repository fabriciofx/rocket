package com.github.fabriciofx.rocket.infra.media;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.github.fabriciofx.rocket.dominio.Fone;
import com.github.fabriciofx.rocket.dominio.Nome;
import com.github.fabriciofx.rocket.dominio.endereco.Bairro;
import com.github.fabriciofx.rocket.dominio.endereco.Cep;
import com.github.fabriciofx.rocket.dominio.endereco.Cidade;
import com.github.fabriciofx.rocket.dominio.endereco.Complemento;
import com.github.fabriciofx.rocket.dominio.endereco.Estado;
import com.github.fabriciofx.rocket.dominio.endereco.Logradouro;
import com.github.fabriciofx.rocket.dominio.endereco.Numero;
import com.github.fabriciofx.rocket.dominio.pessoa.Cpf;
import com.github.fabriciofx.rocket.dominio.pessoa.Pessoa;
import com.github.fabriciofx.rocket.dominio.pessoa.Rg;
import com.github.fabriciofx.rocket.dominio.pessoa.Sexo;
import com.github.fabriciofx.rocket.dominio.pessoa.Tratamento;
import com.github.fabriciofx.rocket.dominio.simples.SimplesEndereco;
import com.github.fabriciofx.rocket.dominio.simples.SimplesFone;
import com.github.fabriciofx.rocket.dominio.simples.SimplesPessoa;

public final class TesteSqlMedia {
	@Test
	public void insert() throws IOException {
		final Pessoa pessoa = new SimplesPessoa(
				new Nome("José de Alencar"),
				Sexo.MASCULINO,
				Tratamento.SENHOR,
				new Cpf("60840226772"),
				new Rg("12345678"),
				new SimplesEndereco(
					new Logradouro("Av Gov Torquato Nepomuceno Neves"),
					new Numero("123"),
					new Complemento("AP 101"),
					new Bairro("Vila Madalena"),
					new Cidade("São Paulo", Estado.SP),
					new Cep("48035120")
				),			
				new SimplesFone("999918967", Fone.Tipo.CELULAR, Fone.Operadora.TIM)
			);
		assertEquals(
			"INSERT INTO pessoa (nome, sexo, tratamento, cpf, rg, "
			+ "logradouro, numero, complemento, bairro, cidade, cep, numero, "
			+ "tipo, operadora) VALUES ('José de Alencar', 'MASCULINO', "
			+ "'SENHOR', '60840226772', '12345678 SSP-PB', "
			+ "'Av Gov Torquato Nepomuceno Neves', '123', 'AP 101', "
			+ "'Vila Madalena', 'São Paulo-SP', '48035120', '999918967', '"
			+ "CELULAR', 'TIM')",
			pessoa.print(new SqlMedia("pessoa")).toString()
		);
	}
}
