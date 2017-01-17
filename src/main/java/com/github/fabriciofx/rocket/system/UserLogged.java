package com.github.fabriciofx.rocket.system;

import java.io.IOException;

public interface UserLogged extends User {
	boolean logged() throws IOException;
	
	final UserLogged ANONYMOUS = new UserSmartLogged(User.ANONYMOUS, false);
}
