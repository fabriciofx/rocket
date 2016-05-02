package com.github.fabriciofx.rocket.dominio;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public final class TesteUsuario {
	@Test
	public void padrao() throws IOException {
		final Usuario u = new Usuario.Padrao(new Nome("Homer Simpson"),
				new Email("homer@fox.com"), new Senha("D'oh!"));
		assertEquals("Homer Simpson", u.nome().completo());
		assertEquals("homer@fox.com", u.email().toString());
		assertEquals(
			"a60eeddc811e87a946c3885062c3bad2fc100712cae3dadafdc33dca8ce3e7ed",
			u.senha().toString()
		);
	}
}
