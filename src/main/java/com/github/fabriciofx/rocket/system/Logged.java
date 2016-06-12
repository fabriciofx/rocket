package com.github.fabriciofx.rocket.system;

import java.io.IOException;

public interface Logged extends User {
	boolean logged() throws IOException;

	Logged USER = new SimpleLogged(User.ANONYMOUS, false);
}
