package com.github.fabriciofx.rocket.user;

import java.io.IOException;
import java.util.Map;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class UserSmartLogged implements UserLogged {
	private final User origin;
	private final boolean logged;
	
	public UserSmartLogged(final User origin) {
		this(origin, true);
	}
	
	public UserSmartLogged(final User origin, final boolean logged) {
		this.origin = origin;
		this.logged = logged;
	}
	
	@Override
	public String name() throws IOException {
		return this.origin.name();
	}

	@Override
	public String password() throws IOException {
		return this.origin.password();
	}

	@Override
	public Map<String, String> properties() throws IOException {
		return this.origin.properties();
	}

	@Override
	public boolean logged() throws IOException {
		return this.logged;
	}
}
