package com.github.fabriciofx.rocket.db;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;

public final class User {
	private final transient String name;
	private final transient String password;

	public User(final String name, final String password) {
		this.name = new NotEmpty<String>(new NotNull<>()).valid(name);
		this.password = new NotNull<String>().valid(password);
	}

	public String name() {
		return name;
	}

	public String password() {
		return password;
	}
}
