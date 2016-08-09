package com.github.fabriciofx.rocket.db;

import java.io.IOException;

import javax.sql.DataSource;

public interface ConnectionPool {
	DataSource source() throws IOException;
}
