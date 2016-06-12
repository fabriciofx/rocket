package com.github.fabriciofx.rocket.db;

import java.io.IOException;

public interface Consulta<T> {
	T execute(final Conexao conexao) throws IOException;
}
