package com.github.fabriciofx.rocket.financeiro;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Locale;

import org.junit.Test;

public final class TesteParcelas {
	@Test
	public void distribuaDezReaisESetentaCentavosEm3Parcelas() {
		final List<Dinheiro> parcelas = new Parcelas(
			new Dinheiro("10.70"),
			3
		).parcelas();
		assertEquals("R$ 3,56", parcelas.get(0).toString());
		assertEquals("R$ 3,56", parcelas.get(1).toString());
		assertEquals("R$ 3,58", parcelas.get(2).toString());
	}

	@Test
	public void distribuaDezDolaresESetentaCentavosEm3Parcelas() {
		final List<Dinheiro> parcelas = new Parcelas(
				new Dinheiro("10.70", "USD"),
				3
			).parcelas();
		assertEquals("$ 3.56", parcelas.get(0).toString(Locale.US));
		assertEquals("$ 3.56", parcelas.get(1).toString(Locale.US));
		assertEquals("$ 3.58", parcelas.get(2).toString(Locale.US));
	}
}
