package com.github.fabriciofx.rocket.db;

import java.io.IOException;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.system.User;

public interface Database {
	User user();

	Url url();

	DataSource source() throws IOException;
	
	void exec(SqlScript script) throws IOException;
}
