package com.github.fabriciofx.rocket.system;

import java.io.IOException;

public interface LoggedUser extends User {
	boolean logged() throws IOException;
	
	final LoggedUser ANONYMOUS = new SmartLoggedUser(User.ANONYMOUS, false);
}
