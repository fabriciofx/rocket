package com.github.fabriciofx.rocket.user;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.github.fabriciofx.rocket.email.EmailSimple;
import com.github.fabriciofx.rocket.password.PasswordCrypted;
import com.github.fabriciofx.rocket.password.PasswordPlain;

public final class UserTest {
	@Test
	public void user() throws IOException {
		final User user = new UserSmart(
				"Homer Simpson",
				new PasswordCrypted(new PasswordPlain("D'oh!"))
		);
		assertEquals("Homer Simpson", user.name());
		assertEquals(
			"a60eeddc811e87a946c3885062c3bad2fc100712cae3dadafdc33dca8ce3e7ed",
			user.password()
		);
	}

	@Test
	public void emailed() throws IOException {
		final User user = new UserSmart(
				new EmailSimple("homer@fox.com"),
				new PasswordCrypted(new PasswordPlain("D'oh!"))
		);
		assertEquals("homer@fox.com", user.name());
		assertEquals(
			"a60eeddc811e87a946c3885062c3bad2fc100712cae3dadafdc33dca8ce3e7ed",
			user.password()
		);
	}
}
