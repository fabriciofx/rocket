package com.github.fabriciofx.rocket.financeiro;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

import com.github.fabriciofx.rocket.constraint.NotNull;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class Dinheiro implements Comparable<Dinheiro> {
	private final Currency moeda;
	private final BigDecimal quantia;

	public Dinheiro() {
		this("0");
	}

	public Dinheiro(final String quantia) {
		this(quantia, "BRL");
	}

	public Dinheiro(final BigDecimal quantia) {
		this(quantia, "BRL");
	}

	public Dinheiro(final String quantia, final String isoMoeda) {
		this(new BigDecimal(quantia), isoMoeda);
	}

	public Dinheiro(final BigDecimal quantia, final String isoMoeda) {
		this(quantia, Currency.getInstance(isoMoeda));
	}

	public Dinheiro(final BigDecimal quantia, final Currency moeda) {
		this.quantia = quantia;
		this.moeda = moeda;
	}

	public Currency moeda() {
		return new NotNull<Currency>().valid(moeda);
	}

	public BigDecimal quantia() {
		return new NotNull<BigDecimal>().valid(quantia);
	}

	public Dinheiro negativo() {
		return multiplica(-1);
	}

	public boolean negativado() {
		return quantia().signum() == -1;
	}

	public Dinheiro soma(final Dinheiro dinheiro) {
		verificaMoeda(dinheiro);
		return new Dinheiro(quantia().add(dinheiro.quantia), moeda);
	}

	public Dinheiro subtraia(final Dinheiro dinheiro) {
		verificaMoeda(dinheiro);
		return new Dinheiro(quantia.subtract(dinheiro.quantia), moeda);
	}

	public Dinheiro multiplica(final Number numero) {
		return new Dinheiro(
			quantia().multiply(
				new BigDecimal(new NotNull<Number>().valid(numero).longValue())
			),
			moeda()
		);
	}


	public String toString(final Locale localizacao) {
		final NumberFormat formato = NumberFormat.getInstance(
			new NotNull<Locale>().valid(localizacao)
		);
		formato.setMinimumFractionDigits(moeda.getDefaultFractionDigits());
		return String.format(
			"%s %s",
			moeda.getSymbol(localizacao),
			formato.format(quantia)
		);
	}

	@Override
	public String toString() {
		return toString(new Locale("pt", "BR"));
	}

	@Override
	public int compareTo(final Dinheiro dinheiro) {
		return quantia.compareTo(verificaMoeda(dinheiro).quantia);
	}
	
	private Dinheiro verificaMoeda(final Dinheiro dinheiro) {
		if (!moeda.equals(new NotNull<Dinheiro>().valid(dinheiro).moeda)) {
			throw new IllegalArgumentException(
				"não é possivel comparar valores com moedas diferentes"
			);
		}
		return dinheiro;
	}
}
