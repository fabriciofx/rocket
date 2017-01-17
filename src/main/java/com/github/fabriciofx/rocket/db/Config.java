package com.github.fabriciofx.rocket.db;

import java.io.IOException;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.system.User;

public final class Config {
	private final ConnectionPool cp;
	private final Url url;
	private final User user;

	public Config(final Url url, final User user) throws IOException {
		this(new ConnectionPoolHikari(url, user), url, user);
	}

	public Config(final ConnectionPool cp, final Url url, final User user) {
		this.cp = cp;
		this.url = url;
		this.user = user;
	}

	public User user() {
		return new NotNull<User>().valid(user);
	}

	public Url url() {
		return new NotNull<Url>().valid(url);
	}

	public DataSource source() throws IOException {
		return new NotNull<ConnectionPool>().valid(cp).source();
	}
}
