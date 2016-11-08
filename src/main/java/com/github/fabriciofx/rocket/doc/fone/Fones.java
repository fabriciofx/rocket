package com.github.fabriciofx.rocket.doc.fone;

import java.io.IOException;
import java.util.List;

import com.github.fabriciofx.rocket.doc.fone.Fone.Operadora;
import com.github.fabriciofx.rocket.doc.fone.Fone.Tipo;
import com.github.fabriciofx.rocket.media.Printer;

public interface Fones extends Printer {
	void adicione(String numero, Tipo tipo, Operadora operadora)
		throws IOException;
	
	List<Fone> todos() throws IOException;
}
