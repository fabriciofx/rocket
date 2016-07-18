package com.github.fabriciofx.rocket.system.simple;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.github.fabriciofx.rocket.dominio.doc.Email;
import com.github.fabriciofx.rocket.security.Password;
import com.github.fabriciofx.rocket.system.User;

public final class UserTest {
	@Test
	public void simple() throws IOException {
		final User user = new SimpleUser(
				new Email("homer@fox.com"),
				new Password("D'oh!")
		);
		assertEquals("homer@fox.com", user.email().toString());
		assertEquals(
			"a60eeddc811e87a946c3885062c3bad2fc100712cae3dadafdc33dca8ce3e7ed",
			user.password().toString()
		);
	}
}
