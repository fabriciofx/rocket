package com.github.fabriciofx.rocket.db;

import java.io.IOException;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.user.User;

public interface Database {
	User user();

	Url url();

	DataSource source() throws IOException;
	
	Database exec(ScriptSql script) throws IOException;
}
