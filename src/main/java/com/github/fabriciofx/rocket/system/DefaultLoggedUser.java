package com.github.fabriciofx.rocket.system;

import java.io.IOException;

public class DefaultLoggedUser implements LoggedUser {
	private final transient User<?> origin;
	private final transient boolean logged;

	public DefaultLoggedUser(final User<?> origin) {
		this(origin, true);
	}
	
	public DefaultLoggedUser(final User<?> origin, final boolean logged) {
		this.origin = origin;
		this.logged = logged;
	}

	@Override
	public User<?> user() throws IOException {
		return origin;
	}

	@Override
	public boolean logged() throws IOException {
		return logged;
	}
}
