package com.github.fabriciofx.rocket.db;

import java.io.IOException;

public interface Query<T> {
	T execute(final Connection connection) throws IOException;
}
