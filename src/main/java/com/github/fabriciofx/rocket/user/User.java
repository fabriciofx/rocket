package com.github.fabriciofx.rocket.user;

import java.io.IOException;
import java.util.Map;

import com.github.fabriciofx.rocket.password.PasswordPlain;

public interface User {	
	String name() throws IOException;

	String password() throws IOException;
	
	Map<String, String> properties() throws IOException;

	final User ANONYMOUS = new UserSmart(
		"anonymous",
		new PasswordPlain("Anonymous")
	);
}
