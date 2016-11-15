package com.github.fabriciofx.rocket.pessoa;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.github.fabriciofx.rocket.doc.fone.Fones;
import com.github.fabriciofx.rocket.id.Id;

public interface Pessoas {
	Pessoa pessoa(Map<String, String> documentos, Fones fones)
		throws IOException;
	
	Pessoa pessoa(Id id) throws IOException;
	
	List<Pessoa> todas() throws IOException;
}
