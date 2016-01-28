package com.github.fabriciofx.rocket.dominio.financeiro;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.fabriciofx.rocket.dominio.financeiro.Conta;
import com.github.fabriciofx.rocket.dominio.financeiro.Dinheiro;

public class TesteConta {
	@Test
	public void criaContaComCreditoDeCemReais() {
		final Conta c = new Conta(new Dinheiro("100.00"));

		assertEquals(c.saldo().toString(), "R$ 100,00");
	}

	@Test
	public void saqueDeDezReais() {
		final Conta c = new Conta(new Dinheiro("100.00"));
		final Conta r = c.saque(new Dinheiro("10.00"));

		assertEquals(c.saldo().toString(), "R$ 100,00");
		assertEquals(r.saldo().toString(), "R$ 90,00");
	}

	@Test
	public void depositoDeDezReais() {
		final Conta c = new Conta(new Dinheiro("100.00"));
		final Conta r = c.deposito(new Dinheiro("10.00"));

		assertEquals(c.saldo().toString(), "R$ 100,00");
		assertEquals(r.saldo().toString(), "R$ 110,00");
	}

	@Test
	public void saquesEDepositos() {
		final Conta c = new Conta(new Dinheiro("100.00"))
				.saque(new Dinheiro("23.47")).deposito(new Dinheiro("11.57"))
				.saque(new Dinheiro("33.41")).saque(new Dinheiro("13.33"));

		assertEquals(c.saldo().toString(), "R$ 41,36");
	}

}
