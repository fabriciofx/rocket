package com.github.fabriciofx.rocket.system;

import java.io.IOException;
import java.util.Map;

public interface User {	
	final User ANONYMOUS = new UserSmart(
		"anonymous",
		new Password("Anonymous")
	);
	
	String name() throws IOException;

	String password() throws IOException;
	
	Map<String, String> properties() throws IOException;
}
