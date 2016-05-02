package com.github.fabriciofx.rocket.dominio;

import static org.hamcrest.CoreMatchers.containsString;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@Ignore
@RunWith(Parameterized.class)
public final class TestePlaca {
	private String numero;
	private Class<? extends Throwable> excecao;
	private String mensagem;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Parameters(name = "Placa[{0}]: {2}")
	public static Collection<Object[]> dadosInvalidos() {
		return Arrays.asList(new Object[][] {
				{ null, IllegalArgumentException.class,
						"número de placa inválido" },
				{ "", IllegalArgumentException.class,
						"número de placa inválido" },
				{ "1234567", IllegalArgumentException.class,
						"número de placa inválido" },
				{ "ABCDEFG", IllegalArgumentException.class,
						"número de placa inválido" },
				{ "A1B2C3D", IllegalArgumentException.class,
						"número de placa inválido" },
				{ "ABCD123", IllegalArgumentException.class,
						"número de placa inválido" },
				{ "1234ABC", IllegalArgumentException.class,
						"número de placa inválido" },
				{ "1234567", IllegalArgumentException.class,
						"número de placa inválido" },
				{ "1234567", IllegalArgumentException.class,
						"número de placa inválido" },
				{ "ABC123", IllegalArgumentException.class,
						"número de placa inválido" },
				{ "ABCD123", IllegalArgumentException.class,
						"número de placa inválido" },
				{ "123-4567", IllegalArgumentException.class,
						"número de placa inválido" },
				{ "ABC-DEFG", IllegalArgumentException.class,
						"número de placa inválido" },
				{ "A1B-2C3D", IllegalArgumentException.class,
						"número de placa inválido" },
				{ "ABC-D123", IllegalArgumentException.class,
						"número de placa inválido" },
				{ "123-4ABC", IllegalArgumentException.class,
						"número de placa inválido" },
				{ "123-4567", IllegalArgumentException.class,
						"número de placa inválido" },
				{ "123-4567", IllegalArgumentException.class,
						"número de placa inválido" },
				{ "ABC-123", IllegalArgumentException.class,
						"número de placa inválido" },
				{ "ABC-D123", IllegalArgumentException.class,
						"número de placa inválido" },
				{ "ABC.1234", IllegalArgumentException.class,
						"número de placa inválido" },
				{ "ABC,1234", IllegalArgumentException.class,
						"número de placa inválido" },
				{ "ABC1234.", IllegalArgumentException.class,
						"número de placa inválido" }, });
	}

	public TestePlaca(String numero, Class<? extends Throwable> excecao,
			String mensagem) {
		this.numero = numero;
		this.excecao = excecao;
		this.mensagem = mensagem;
	}

	@Test
	public void invalidos() {
		exception.expect(excecao);
		exception.expectMessage(containsString(mensagem));
		new Placa(numero);
	}
}
