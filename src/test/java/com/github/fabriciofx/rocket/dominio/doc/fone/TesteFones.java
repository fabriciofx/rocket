package com.github.fabriciofx.rocket.dominio.doc.fone;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.github.fabriciofx.rocket.media.XmlFormat;
import com.github.fabriciofx.rocket.media.XmlMedia;

public final class TesteFones {
	@Test
	public void xml() throws IOException {
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
		final String ls = System.lineSeparator();
		final String xml =
		"<fones>" + ls +
		"  <fone>" + ls +
		"    <numero>81999452345</numero>" + ls +
		"    <tipo>CELULAR</tipo>" + ls +
		"    <operadora>TIM</operadora>" + ls +
		"  </fone>" + ls +
		"  <fone>" + ls +
		"    <numero>83889452666</numero>" + ls +
		"    <tipo>CELULAR</tipo>" + ls +
		"    <operadora>OI</operadora>" + ls +
		"  </fone>" + ls +
		"</fones>" + ls;
		assertEquals(
				xml,
				new XmlFormat(fones.print(new XmlMedia("fones"))
			).toString()
		);
	}
}
