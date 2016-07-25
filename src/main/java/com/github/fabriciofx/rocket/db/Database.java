package com.github.fabriciofx.rocket.db;

import java.io.IOException;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.system.NamedUser;

public interface Database {
	NamedUser user();

	Url url();

	DataSource dataSource() throws IOException;
}
