package com.github.fabriciofx.rocket.financeiro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.junit.Test;

public final class TesteDinheiro {
	@Test(expected = IllegalArgumentException.class)
	public void invalido1() {
		new Dinheiro("", "");
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalido2() {
		new Dinheiro("1", "");
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalido3() {
		new Dinheiro("23a5.00", "BRL");
	}

	@Test
	public void zeroReais() {
		final Dinheiro zero = new Dinheiro();
		assertEquals("R$ 0,00", zero.toString());
	}

	@Test
	public void umReal() {
		final Dinheiro um = new Dinheiro("1.00");
		assertEquals("R$ 1,00", um.toString());
	}

	@Test
	public void dezReaisESetentaCentavos() {
		final Dinheiro d1 = new Dinheiro("10.70");
		assertEquals("R$ 10,70", d1.toString());
	}

	@Test
	public void maisDeCentoEVinteTresMilhoesDeReais() {
		final Dinheiro d1 = new Dinheiro("123456789.10");
		assertEquals("R$ 123.456.789,10", d1.toString());
	}

	@Test
	public void zeroDolares() {
		final Dinheiro zero = new Dinheiro("0.00", "USD");
		assertEquals("$ 0.00", zero.toString(Locale.US));
	}

	@Test
	public void umDolar() {
		final Dinheiro zero = new Dinheiro("1.00", "USD");
		assertEquals("$ 1.00", zero.toString(Locale.US));
	}

	@Test
	public void dezDolaresESetentaCents() {
		final Dinheiro d1 = new Dinheiro("10.70", "USD");
		assertEquals("$ 10.70", d1.toString(Locale.US));
	}

	@Test
	public void maisDeCentoEVinteTresMilhoesDeDolares() {
		final Dinheiro d1 = new Dinheiro("123456789.10", "USD");
		assertEquals("$ 123,456,789.10", d1.toString(Locale.US));
	}

	@Test
	public void somaDuasQuantiasEmReais() {
		final Dinheiro d1 = new Dinheiro("8298312.76");
		final Dinheiro d2 = new Dinheiro("72738272.82");
		final Dinheiro soma = d1.soma(d2);
		assertEquals("R$ 81.036.585,58", soma.toString());
	}

	@Test
	public void subtraiDuasQuantiasEmReais() {
		final Dinheiro d1 = new Dinheiro("72738272.82");
		final Dinheiro d2 = new Dinheiro("8298312.76");
		final Dinheiro subtracao = d1.subtraia(d2);
		assertEquals("R$ 64.439.960,06", subtracao.toString());
	}

	@Test
	public void multiplicaUmaQuantiaEmReaisPorUmNumero() {
		final Dinheiro d1 = new Dinheiro("8298312.76");
		final Dinheiro multiplicacao = d1.multiplica(7);
		assertEquals("R$ 58.088.189,32", multiplicacao.toString());
	}

	@Test
	public void comparaDuasQuantiasDiferentes() {
		final Dinheiro d1 = new Dinheiro("72738272.82");
		final Dinheiro d2 = new Dinheiro("8298312.76");
		assertFalse(d1.equals(d2));
	}

	@Test
	public void comparaDuasQuantiasIguais() {
		final Dinheiro d1 = new Dinheiro("72738272.82");
		final Dinheiro d2 = new Dinheiro("72738272.82");
		assertTrue(d1.equals(d2));
	}

	@Test
	public void comparaDuasQuantiasIguaisMasDeMoedasDiferentes() {
		final Dinheiro d1 = new Dinheiro("72738272.82");
		final Dinheiro d2 = new Dinheiro("72738272.82", "USD");
		assertFalse(d1.equals(d2));
	}

	@Test
	public void comparaQualQuantiaEhMaior() {
		final Dinheiro d1 = new Dinheiro("72738272.82");
		final Dinheiro d2 = new Dinheiro("72738272.81");
		assertTrue(d1.compareTo(d2) > 0);
	}

	@Test
	public void comparaQualQuantiaEhMenor() {
		final Dinheiro d1 = new Dinheiro("72738272.82");
		final Dinheiro d2 = new Dinheiro("72738272.81");
		assertTrue(d2.compareTo(d1) < 0);
	}

	@Test
	public void negativaQuantia() {
		final Dinheiro d1 = new Dinheiro("72738272.82");
		assertTrue(d1.negativo().compareTo(new Dinheiro("0.00")) < 0);
	}
}
