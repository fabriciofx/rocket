package com.github.fabriciofx.rocket.doc.fone;

import java.io.IOException;
import java.util.List;

import com.github.fabriciofx.rocket.doc.Documento;
import com.github.fabriciofx.rocket.doc.fone.Fone.Operadora;
import com.github.fabriciofx.rocket.doc.fone.Fone.Tipo;

public interface Fones extends Documento {
	void adicione(String numero, Tipo tipo, Operadora operadora)
		throws IOException;
	
	List<Fone> todos() throws IOException;
}
