package com.github.fabriciofx.rocket.dominio;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.fabriciofx.rocket.doc.Nome;
import com.github.fabriciofx.rocket.media.TextMedia;

public final class TesteNome {
	@Test(expected = IllegalArgumentException.class)
	public void invalidoSeNulo() {
		new Nome(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidoSeVazio() {
		new Nome("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidoSeContemNumero() {
		new Nome("Fabrício Barro3 Cabral");
	}

	@Test
	public void validoSePossuirHifen() {
		final Nome nome = new Nome("Catherine Zeta-Jones");
		assertEquals(
			"Catherine Zeta-Jones",
			nome.print(new TextMedia()).toString()
		);
	}

	@Test
	public void validoSePossuirApostrofo() {
		final Nome nome = new Nome("Richard O'Reilly");
		assertEquals(
			"Richard O'Reilly",
			nome.print(new TextMedia()).toString()
		);
	}

	@Test
	public void validoSePossuirAbraviacao() {
		final Nome nome = new Nome("Armando B. C. Gomes");
		assertEquals(
			"Armando B. C. Gomes",
			nome.print(new TextMedia()).toString()
		);
	}
}
