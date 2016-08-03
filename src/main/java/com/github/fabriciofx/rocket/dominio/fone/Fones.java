package com.github.fabriciofx.rocket.dominio.fone;

import java.io.IOException;
import java.util.List;

import com.github.fabriciofx.rocket.dominio.fone.Fone.Operadora;
import com.github.fabriciofx.rocket.dominio.fone.Fone.Tipo;
import com.github.fabriciofx.rocket.media.Printer;

public interface Fones extends Printer {
	List<Fone> todos() throws IOException;

	void salva(String numero, Tipo tipo, Operadora operadora)
		throws IOException;
}
