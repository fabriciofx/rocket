package com.github.fabriciofx.rocket.system;

import java.io.IOException;
import java.util.Map;

public interface User {
	String name() throws IOException;

	String password() throws IOException;
	
	Map<String, String> properties() throws IOException;
	
	final User ANONYMOUS = new SmartUser(
		"anonymous",
		new Password("Anonymous")
	);
}
