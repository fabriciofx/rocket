package com.github.fabriciofx.rocket.dominio;

import java.io.IOException;

import com.github.fabriciofx.rocket.media.Printer;

public interface Fones extends Printer {
	Fone fone(String numero) throws IOException;
	
	void adiciona(String numero) throws IOException;
}
