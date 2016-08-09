package com.github.fabriciofx.rocket.db;

import java.io.IOException;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.system.NamedUser;

public final class Base {
	private final transient ConnectionPool cp;
	private final transient Url url;
	private final transient NamedUser user;

	public Base(final Url url, final NamedUser user) throws IOException {
		this(new HikariConnectionPool(url, user), url, user);
	}

	public Base(final ConnectionPool cp, final Url url, final NamedUser user) {
		this.cp = cp;
		this.url = url;
		this.user = user;
	}

	public NamedUser user() {
		return new NotNull<NamedUser>().valid(user);
	}

	public Url url() {
		return new NotNull<Url>().valid(url);
	}

	public DataSource source() throws IOException {
		return new NotNull<ConnectionPool>().valid(cp).source();
	}
}
