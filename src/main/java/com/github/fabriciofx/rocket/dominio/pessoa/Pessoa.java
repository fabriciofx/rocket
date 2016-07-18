package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.IOException;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.dominio.repositorio.Id;
import com.github.fabriciofx.rocket.dominio.repositorio.Identificavel;
import com.github.fabriciofx.rocket.media.Printer;

public interface Pessoa extends Identificavel<Id>, Printer {
	void save(DataSource ds) throws IOException;
	
	Pessoa find(DataSource ds, Id id) throws IOException;
}
