package com.github.fabriciofx.rocket.dominio.financeiro;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.constraint.Positive;

public final class Dinheiro implements Comparable<Dinheiro> {
	private final transient Currency moeda;
	private final transient BigDecimal quantia;

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
		this.quantia = new NotNull<BigDecimal>().valid(quantia);
		this.moeda = new NotNull<Currency>().valid(moeda);
	}

	public Currency moeda() {
		return moeda;
	}

	public BigDecimal quantia() {
		return quantia;
	}

	public Dinheiro negativo() {
		return multiplica(-1);
	}

	public boolean estaNegativo() {
		return quantia.signum() == -1;
	}

	public Dinheiro soma(final Dinheiro dinheiro) {
		verificaMoeda(dinheiro);
		return new Dinheiro(quantia.add(dinheiro.quantia), moeda);
	}

	public Dinheiro subtraia(final Dinheiro dinheiro) {
		verificaMoeda(dinheiro);
		return new Dinheiro(quantia.subtract(dinheiro.quantia), moeda);
	}

	public Dinheiro multiplica(final Number numero) {
		return new Dinheiro(
			quantia.multiply(
				new BigDecimal(new NotNull<Number>().valid(numero).longValue())
			),
			moeda
		);
	}

	public List<Dinheiro> parcela(final Number numero) {
		final long num = new Positive<Number>(
			new NotNull<>()
		).valid(numero).longValue();
		final long centavos = deBigDecimalParaLong(quantia, moeda);
		final long quociente = centavos / num;
		final long resto = centavos % num;
		final List<Dinheiro> parcelas = new ArrayList<>();
		for (int i = 0; i < num - 1; i++) {
			parcelas.add(
				new Dinheiro(
					deLongParaBigDecimal(quociente, moeda),
					moeda
				)
			);
		}
		parcelas.add(
			new Dinheiro(
				deLongParaBigDecimal(quociente + resto, moeda),
				moeda
			)
		);
		return Collections.unmodifiableList(parcelas);
	}

	public String toString(final Locale localizacao) {
		final NumberFormat formatoMoeda = NumberFormat.getInstance(
			new NotNull<Locale>().valid(localizacao)
		);
		formatoMoeda.setMinimumFractionDigits(moeda.getDefaultFractionDigits());
		return String.format(
			"%s %s",
			moeda.getSymbol(localizacao),
			formatoMoeda.format(quantia)
		);
	}

	@Override
	public String toString() {
		return toString(new Locale("pt", "BR"));
	}

	@Override
	public int hashCode() {
		return 31 * (31 + ((moeda == null) ? 0 : moeda.hashCode()))
				+ ((quantia == null) ? 0 : quantia.hashCode());
	}

	@Override
	public boolean equals(final Object o) {
		return o != null && o instanceof Dinheiro
				&& Dinheiro.class.cast(o).moeda.equals(moeda)
				&& Dinheiro.class.cast(o).quantia.equals(quantia);
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

	private long deBigDecimalParaLong(final BigDecimal quantia,
			final Currency moeda) {
		return quantia.movePointRight(moeda.getDefaultFractionDigits())
				.longValue();
	}

	private BigDecimal deLongParaBigDecimal(final long quantia,
			final Currency moeda) {
		return new BigDecimal(quantia)
				.movePointLeft(moeda.getDefaultFractionDigits());
	}
}
