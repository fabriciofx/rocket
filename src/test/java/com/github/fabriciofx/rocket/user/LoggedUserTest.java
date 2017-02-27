package com.github.fabriciofx.rocket.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.github.fabriciofx.rocket.email.EmailSimple;
import com.github.fabriciofx.rocket.password.Password;
import com.github.fabriciofx.rocket.user.UserLogged;
import com.github.fabriciofx.rocket.user.UserSmart;
import com.github.fabriciofx.rocket.user.UserSmartLogged;

public final class LoggedUserTest {
	@Test
	public void loggedUser() throws IOException {
		final UserLogged loggedUser = new UserSmartLogged(
			new UserSmart(
				new EmailSimple("homer@fox.com"),
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
