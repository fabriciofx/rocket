package com.github.fabriciofx.rocket.infra.bd;

import java.io.IOException;

public interface Comando {
	void execute(Conexao conexao) throws IOException;
}
