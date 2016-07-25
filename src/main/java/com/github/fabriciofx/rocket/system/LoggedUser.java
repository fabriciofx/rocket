package com.github.fabriciofx.rocket.system;

import java.io.IOException;

public interface LoggedUser {
	User<?> user() throws IOException;
	
	boolean logged() throws IOException;

	LoggedUser DEFAULT = new DefaultLoggedUser(User.NAMED_ANONYMOUS,
			false);
}
