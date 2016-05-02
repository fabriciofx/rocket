package com.github.fabriciofx.rocket.dominio.pessoa;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.github.fabriciofx.rocket.dominio.endereco.Bairro;
import com.github.fabriciofx.rocket.dominio.endereco.Cep;
import com.github.fabriciofx.rocket.dominio.endereco.Cidade;
import com.github.fabriciofx.rocket.dominio.endereco.Complemento;
import com.github.fabriciofx.rocket.dominio.endereco.Endereco;
import com.github.fabriciofx.rocket.dominio.endereco.Estado;
import com.github.fabriciofx.rocket.dominio.endereco.Logradouro;
import com.github.fabriciofx.rocket.dominio.endereco.Numero;
import com.github.fabriciofx.rocket.dominio.pessoa.Cpf;
import com.github.fabriciofx.rocket.dominio.pessoa.Fone;
import com.github.fabriciofx.rocket.dominio.pessoa.Nome;
import com.github.fabriciofx.rocket.dominio.pessoa.Pessoa;
import com.github.fabriciofx.rocket.dominio.pessoa.Rg;
import com.github.fabriciofx.rocket.dominio.pessoa.Sexo;
import com.github.fabriciofx.rocket.dominio.pessoa.Tratamento;
import com.github.fabriciofx.rocket.dominio.repositorio.DataId;
import com.github.fabriciofx.rocket.infra.media.XmlMedia;

public final class TestePessoa {
	private final String LS = System.lineSeparator();
	private final Pessoa pessoa = new Pessoa.Simples(
			new DataId(2015123101325444663L),
			new Nome("José de Alencar"),
			new Fone("999918967", Fone.Tipo.CELULAR, Fone.Operadora.TIM),
			Sexo.MASCULINO,
			Tratamento.SENHOR,
			new Cpf("03247407430"),
			new Rg("12345678"),
			new Endereco.Simples(
				new Logradouro("Av Gov Torquato Nepomuceno Neves"),
				new Numero("123"),
				new Complemento("AP 101"),
				new Bairro("Vila Madalena"),
				new Cidade("São Paulo", Estado.SP),
				new Cep("48035120")
			)			
		);

	@Test
	public void xml() {
		final String xml = 
			"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" + LS +
			"<pessoa>" + LS +
			"<id>2015123101325444663</id>" + LS +
			"<nome>José de Alencar</nome>" + LS +
			"<fone>999918967 (TIM CELULAR)</fone>" + LS +
			"<sexo>MASCULINO</sexo>" + LS +
			"<tratamento>Sr.</tratamento>" + LS +
			"<cpf>03247407430</cpf>" + LS +
			"<rg>12345678 SSP/PB</rg>" + LS +
			"<simples>Av Gov Torquato Nepomuceno Neves;123;AP 101;Vila Madalena;São Paulo-SP;48035120;</simples>" + LS +
			"</pessoa>" + LS;
		assertEquals(xml, pessoa.imprime(new XmlMedia("pessoa")).toString());
	}
	
	@Test
	public void obtemCpf() throws IOException {
		final Cpf cpf = pessoa.elemento(Cpf.class);
		assertEquals("03247407430", cpf.toString());
	}
}
