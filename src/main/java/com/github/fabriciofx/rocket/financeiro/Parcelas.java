package com.github.fabriciofx.rocket.financeiro;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.List;

import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.constraint.Positive;

public final class Parcelas {
	private final Dinheiro quantia;
	private final int numero;
	
	public Parcelas(final Dinheiro quantia, final int numero) {
		this.quantia = quantia;
		this.numero = numero;
	}
	
	public List<Dinheiro> parcelas() {
		final long num = new Positive<Number>(
			new NotNull<>()
		).valid(numero).longValue();
		final long centavos = deBigDecimalParaLong(
			quantia.quantia(), quantia.moeda()
		);
		final long quociente = centavos / num;
		final long resto = centavos % num;
		final List<Dinheiro> parcelas = new ArrayList<>();
		for (int i = 0; i < num - 1; i++) {
			parcelas.add(
				new Dinheiro(
					deLongParaBigDecimal(quociente, quantia.moeda()),
					quantia.moeda()
				)
			);
		}
		parcelas.add(
			new Dinheiro(
				deLongParaBigDecimal(quociente + resto, quantia.moeda()),
				quantia.moeda()
			)
		);
		return Collections.unmodifiableList(parcelas);
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
