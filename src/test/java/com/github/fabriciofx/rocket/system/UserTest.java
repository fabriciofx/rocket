package com.github.fabriciofx.rocket.system;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.github.fabriciofx.rocket.dominio.Nome;
import com.github.fabriciofx.rocket.dominio.pessoa.doc.Email;

public final class UserTest {
	@Test
	public void named() throws IOException {
		final NamedUser user = new DefaultNamedUser(
				new Nome("Homer Simpson"),
				new Password("D'oh!")
		);
		assertEquals("Homer Simpson", user.name().toString());
		assertEquals(
			"a60eeddc811e87a946c3885062c3bad2fc100712cae3dadafdc33dca8ce3e7ed",
			user.password().toString()
		);
	}

	@Test
	public void emailed() throws IOException {
		final EmailedUser user = new DefaultEmailedUser(
				new Email("homer@fox.com"),
				new Password("D'oh!")
		);
		assertEquals("homer@fox.com", user.name().toString());
		assertEquals(
			"a60eeddc811e87a946c3885062c3bad2fc100712cae3dadafdc33dca8ce3e7ed",
			user.password().toString()
		);
	}
}
