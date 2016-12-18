package com.github.fabriciofx.rocket.fone;

import java.io.IOException;
import java.util.List;

import com.github.fabriciofx.rocket.id.Identificavel;

public interface Fones extends Identificavel {
	Fone fone(String numero, String tipo, String operadora) throws IOException;
	
	List<Fone> todos() throws IOException;
}
