package com.github.fabriciofx.rocket.system;

import java.io.IOException;

import com.github.fabriciofx.rocket.system.simple.SimpleLoggedUser;

public interface LoggedUser extends User {
	boolean logged() throws IOException;

	LoggedUser USER = new SimpleLoggedUser(User.ANONYMOUS, false);
}
