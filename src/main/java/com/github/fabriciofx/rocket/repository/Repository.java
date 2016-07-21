package com.github.fabriciofx.rocket.repository;

import java.io.IOException;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.id.Id;

public interface Repository<T> {
	void save(DataSource ds) throws IOException;
	
	T find(DataSource ds, Id id) throws IOException;
}
