package com.github.fabriciofx.rocket.money;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

public final class MoneySmart implements Money {
	private final Locale locale;
	private final Currency currency;
	private final BigDecimal value;

	public MoneySmart(final double value) {
		this(new BigDecimal(value));
	}

	public MoneySmart(final BigDecimal value) {
		this(new Locale("pt", "BR"), Currency.getInstance("BRL"), value);
	}

	public MoneySmart(final Locale locale, final Currency currency,
		final BigDecimal value) {
		this.locale = locale;
		this.currency = currency;
		this.value = value;
	}
	
	@Override
	public BigDecimal value() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return new PrintedMoney(
			this.locale,
			this.currency,
			this.value
		).toString();
	}
}