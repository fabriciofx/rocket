package com.github.fabriciofx.rocket.system;

import java.io.IOException;

public class DefaultLoggedUser implements LoggedUser {
	private final transient User<?> user;
	private final transient boolean logged;

	public DefaultLoggedUser(final User<?> user) {
		this(user, true);
	}
	
	public DefaultLoggedUser(final User<?> user, final boolean logged) {
		this.user = user;
		this.logged = logged;
	}

	@Override
	public User<?> user() throws IOException {
		return user;
	}

	@Override
	public boolean logged() throws IOException {
		return logged;
	}
}
