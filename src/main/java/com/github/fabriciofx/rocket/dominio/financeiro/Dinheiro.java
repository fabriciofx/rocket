package com.github.fabriciofx.rocket.dominio.financeiro;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;

import com.github.fabriciofx.rocket.dominio.Elemento;
import com.jcabi.immutable.Array;

public final class Dinheiro implements Elemento, Comparable<Dinheiro> {
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
		this.quantia = Objects.requireNonNull(quantia,
				"argumento 'quantia' não pode ser NULL");
		this.moeda = Objects.requireNonNull(moeda,
				"argumento 'moeda' não pode ser NULL");
	}

	public Currency moeda() {
		return moeda;
	}

	public BigDecimal quantia() {
		return quantia;
	}

	public Dinheiro inverte() {
		return multiplica(-1);
	}

	public boolean negativo() {
		return quantia.signum() == -1;
	}

	public Dinheiro soma(final Dinheiro dinheiro) {
		Objects.requireNonNull(dinheiro,
				"argumento 'dinheiro' em Dinheiro#soma não pode ser NULL");

		if (!moeda.equals(dinheiro.moeda)) {
			throw new IllegalArgumentException(
					"não é possivel realizar Dinheiro#soma com moedas diferentes");
		}

		return new Dinheiro(quantia.add(dinheiro.quantia), moeda);
	}

	public Dinheiro subtraia(final Dinheiro dinheiro) {
		Objects.requireNonNull(dinheiro,
				"argumento 'dinheiro' em Dinheiro#subtraia não pode ser NULL");

		if (!moeda.equals(dinheiro.moeda)) {
			throw new IllegalArgumentException(
					"não é possivel realizar Dinheiro#subtraia com moedas diferentes");
		}

		return new Dinheiro(quantia.subtract(dinheiro.quantia), moeda);
	}

	public Dinheiro multiplica(final Number numero) {
		Objects.requireNonNull(numero,
				"argumento 'numero' em Dinheiro#multiplica não pode ser NULL");

		return new Dinheiro(
				quantia.multiply(new BigDecimal(numero.longValue())), moeda);
	}

	public Array<Dinheiro> parcela(final Number numero) {
		Objects.requireNonNull(numero,
				"argumento 'numero' em Dinheiro#parcela não pode ser NULL");

		final long num = numero.longValue();

		if (num <= 0) {
			throw new IllegalArgumentException(
					"argumento 'numero' em Dinheiro#parcela precisa ser maior que 0");
		}

		final long centavos = deBigDecimalParaLong(quantia, moeda);
		final long quociente = centavos / num;
		final long resto = centavos % num;
		Array<Dinheiro> parcelas = new Array<>();

		for (int i = 0; i < num - 1; i++) {
			parcelas = parcelas.with(new Dinheiro(
					deLongParaBigDecimal(quociente, moeda), moeda));
		}

		parcelas = parcelas.with(new Dinheiro(
				deLongParaBigDecimal(quociente + resto, moeda), moeda));

		return parcelas;
	}

	public String toString(final Locale localizacao) {
		Objects.requireNonNull(localizacao,
				"localização da moeda em Dinheiro#toString não pode ser NULL");

		final NumberFormat formatoMoeda = NumberFormat.getInstance(localizacao);
		formatoMoeda.setMinimumFractionDigits(moeda.getDefaultFractionDigits());

		return moeda.getSymbol(localizacao) + " "
				+ formatoMoeda.format(quantia());
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
		Objects.requireNonNull(dinheiro,
				"argumento 'dinheiro' em Dinheiro#compareTo não pode ser NULL");

		if (!moeda.equals(dinheiro.moeda)) {
			throw new IllegalArgumentException(
					"não é possivel realizar Dinheiro#compareTo com moedas diferentes");
		}

		return quantia.compareTo(dinheiro.quantia);
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
