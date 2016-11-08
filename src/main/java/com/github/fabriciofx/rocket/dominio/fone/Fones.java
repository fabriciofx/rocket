package com.github.fabriciofx.rocket.dominio.fone;

import java.io.IOException;
import java.util.List;

import com.github.fabriciofx.rocket.doc.Fone;
import com.github.fabriciofx.rocket.media.Printer;

public interface Fones extends Printer {
	List<Fone> todos() throws IOException;
}
