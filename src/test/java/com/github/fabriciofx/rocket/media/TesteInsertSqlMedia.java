package com.github.fabriciofx.rocket.media;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.github.fabriciofx.rocket.dominio.doc.Cpf;
import com.github.fabriciofx.rocket.dominio.doc.Fone;
import com.github.fabriciofx.rocket.dominio.doc.Nome;
import com.github.fabriciofx.rocket.dominio.doc.Rg;
import com.github.fabriciofx.rocket.dominio.doc.Sexo;
import com.github.fabriciofx.rocket.dominio.doc.Tratamento;
import com.github.fabriciofx.rocket.dominio.doc.endereco.Bairro;
import com.github.fabriciofx.rocket.dominio.doc.endereco.Cep;
import com.github.fabriciofx.rocket.dominio.doc.endereco.Cidade;
import com.github.fabriciofx.rocket.dominio.doc.endereco.Complemento;
import com.github.fabriciofx.rocket.dominio.doc.endereco.Endereco;
import com.github.fabriciofx.rocket.dominio.doc.endereco.Estado;
import com.github.fabriciofx.rocket.dominio.doc.endereco.Logradouro;
import com.github.fabriciofx.rocket.dominio.doc.endereco.Numero;
import com.github.fabriciofx.rocket.dominio.pessoa.Pessoa;
import com.github.fabriciofx.rocket.dominio.pessoa.SimplesPessoa;

public final class TesteInsertSqlMedia {
	@Test
	public void insert() throws IOException {
		final Pessoa pessoa = new SimplesPessoa(
			new Nome("José de Alencar"),
			Sexo.MASCULINO,
			Tratamento.SENHOR,
			new Cpf("60840226772"),
			new Rg("12345678"),
			new Endereco(
				new Logradouro("Av Gov Torquato Nepomuceno Neves"),
				new Numero("123"),
				new Complemento("AP 101"),
				new Bairro("Vila Madalena"),
				new Cidade("São Paulo", Estado.SP),
				new Cep("48035120")
			),			
			new Fone(
				"999918967",
				Fone.Tipo.CELULAR,
				Fone.Operadora.TIM
			)
		);
		assertEquals(
			"INSERT INTO pessoa (nome, sexo, tratamento, cpf, rg, "
			+ "logradouro, numero, complemento, bairro, cidade, estado, cep, "
			+ "numero, tipo, operadora) VALUES ('José de Alencar', "
			+ "'MASCULINO', 'SENHOR', '60840226772', '12345678 SSP-PB', "
			+ "'Av Gov Torquato Nepomuceno Neves', '123', 'AP 101', "
			+ "'Vila Madalena', 'São Paulo', 'SP', '48035120', '999918967', '"
			+ "CELULAR', 'TIM')",
			pessoa.print(new InsertSqlMedia("pessoa")).toString()
		);
	}
}
