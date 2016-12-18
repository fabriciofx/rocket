package com.github.fabriciofx.rocket.pessoa;

import java.io.IOException;
import java.util.Map;

import com.github.fabriciofx.rocket.fone.Fones;
import com.github.fabriciofx.rocket.id.Identificavel;
import com.github.fabriciofx.rocket.media.About;

public interface Pessoa extends About, Identificavel {
	void renomeia(String nome) throws IOException;

	void documentos(Map<String, String> documentos) throws IOException;
	
	Fones fones() throws IOException;
}
