package com.github.fabriciofx.rocket.system;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.github.fabriciofx.rocket.dominio.pessoa.docs.doc.Email;

public final class LoggedUserTest {
	@Test
	public void loggedUser() throws IOException {
		final LoggedUser loggedUser = new SmartLoggedUser(
			new SmartUser(
				new Email("homer@fox.com"),
				new Password("D'oh!")
			)
		);
		assertTrue(loggedUser.logged());
		assertEquals("homer@fox.com", loggedUser.name());
		assertEquals(
			"a60eeddc811e87a946c3885062c3bad2fc100712cae3dadafdc33dca8ce3e7ed",
			loggedUser.password()
		);		
	}
}
