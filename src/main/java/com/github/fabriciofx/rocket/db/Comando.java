package com.github.fabriciofx.rocket.db;

import java.io.IOException;

public interface Comando {
	void execute(Conexao conexao) throws IOException;
}
