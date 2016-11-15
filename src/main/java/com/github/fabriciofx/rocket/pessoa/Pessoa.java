package com.github.fabriciofx.rocket.pessoa;

import java.io.IOException;
import java.util.Map;

import com.github.fabriciofx.rocket.doc.fone.Fones;
import com.github.fabriciofx.rocket.media.About;

public interface Pessoa extends About {
	Fones fones() throws IOException;
	
	void atualize(Map<String, String> documentos, Fones fones)
		throws IOException;
}
