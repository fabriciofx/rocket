package com.github.fabriciofx.rocket.dominio.doc.fone;

import java.io.IOException;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.dominio.doc.Documento;
import com.github.fabriciofx.rocket.dominio.repositorio.Id;
import com.github.fabriciofx.rocket.dominio.repositorio.Identificavel;

public interface Fone extends Identificavel<Id>, Documento {
	enum Operadora {
		TIM, OI, CLARO, AEIOU, GVT, EMBRATEL, TELEFONICA, NEXTEL, VIVO,
		DESCONHECIDO;
	}

	enum Tipo {
		CELULAR, FIXO, RADIO, DESCONHECIDO;
	}
	
	void save(DataSource ds) throws IOException;
	
	Fone find(DataSource ds, Id id) throws IOException;
}
