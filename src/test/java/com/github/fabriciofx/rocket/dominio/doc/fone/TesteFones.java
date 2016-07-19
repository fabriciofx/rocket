package com.github.fabriciofx.rocket.dominio.doc.fone;

import java.io.IOException;

import org.junit.Test;

import com.github.fabriciofx.rocket.media.XmlFormat;
import com.github.fabriciofx.rocket.media.XmlMedia;

public final class TesteFones {
	@Test
	public void fones() throws IOException {
		final Fones fones = new Fones(
			new SimplesFone(
				"81999452345",
				Fone.Tipo.CELULAR,
				Fone.Operadora.TIM
			),
			new SimplesFone(
				"83889452666",
				Fone.Tipo.CELULAR,
				Fone.Operadora.OI
			)
		);
		System.out.println(new XmlFormat(fones.print(new XmlMedia("fones"))));
	}
}
