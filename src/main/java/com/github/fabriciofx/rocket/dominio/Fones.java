package com.github.fabriciofx.rocket.dominio;

import java.io.IOException;
import java.util.List;

import com.github.fabriciofx.rocket.media.Printer;

public interface Fones extends Printer {
	Fone fone(String numero) throws IOException;

	List<Fone> todos() throws IOException;
	
	void salva(Fones origem) throws IOException;
}
