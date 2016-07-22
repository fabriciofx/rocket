package com.github.fabriciofx.rocket.dominio;

import java.io.IOException;
import java.util.List;

import com.github.fabriciofx.rocket.id.Identificavel;
import com.github.fabriciofx.rocket.media.Printer;

public interface Pessoa extends Identificavel, Printer {
	Fones fones() throws IOException;
	
	Pessoa salva(String nome, List<String> fones) throws IOException;
}
