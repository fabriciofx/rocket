package com.github.fabriciofx.rocket.infra.bd;

import java.io.IOException;

public interface Consulta<T> {
	T execute(final Conexao conexao) throws IOException;
}
