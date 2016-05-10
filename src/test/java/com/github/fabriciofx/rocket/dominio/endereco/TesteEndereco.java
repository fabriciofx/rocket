package com.github.fabriciofx.rocket.dominio.endereco;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.github.fabriciofx.rocket.infra.media.TextMedia;
import com.github.fabriciofx.rocket.infra.media.XmlMedia;

public final class TesteEndereco {
	private final String LS = System.lineSeparator();
	private final Endereco endereco = new Endereco.Simples(
		new Logradouro("Av Gov Torquato Nepomuceno Neves"),
		new Numero("123"),
		new Complemento("AP 101"),
		new Bairro("Vila Madalena"),
		new Cidade("São Paulo", Estado.SP),
		new Cep("48035120")
	);

	@Test
	public void texto() {
		final String txt = 
			"Av Gov Torquato Nepomuceno Neves" + LS +
			"123" + LS +
			"AP 101" + LS +
			"Vila Madalena" + LS +
			"São Paulo-SP" + LS +
			"48035120" + LS;
		assertEquals(txt, endereco.imprime(new TextMedia()).toString());
	}
	
	@Test
	public void xml() {
		final String xml = 
			"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" + LS+
			"<endereco>" + LS +
			"<logradouro>Av Gov Torquato Nepomuceno Neves</logradouro>" + LS +
			"<numero>123</numero>" + LS +
			"<complemento>AP 101</complemento>" + LS +
			"<bairro>Vila Madalena</bairro>" + LS +
			"<cidade>São Paulo-SP</cidade>" + LS +
			"<cep>48035120</cep>" + LS +
			"</endereco>" + LS;
		assertEquals(xml, endereco.imprime(new XmlMedia("endereco")).
				toString());
	}
	
	@Test
	public void cep() throws IOException {
		final String cep = endereco.elemento("cep");
		assertEquals("48035120", cep);
	}
}
