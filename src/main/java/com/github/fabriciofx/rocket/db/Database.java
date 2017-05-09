package com.github.fabriciofx.rocket.db;

import java.io.IOException;

import javax.sql.DataSource;

public interface Database {
	String url();

	DataSource source() throws IOException;
	
	Database exec(ScriptSql script) throws IOException;
}
