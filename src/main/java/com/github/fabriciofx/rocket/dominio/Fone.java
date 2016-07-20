package com.github.fabriciofx.rocket.dominio;

import java.io.IOException;

import com.github.fabriciofx.rocket.media.Printer;

public interface Fone extends Printer {
	void apaga() throws IOException;
}
