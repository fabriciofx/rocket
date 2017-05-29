package com.github.fabriciofx.rocket.money;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public final class PrintedMoney {
	private final Locale locale;
	private final Currency currency;
	private final BigDecimal value;

	public PrintedMoney(final Locale locale, final Currency currency,
		final BigDecimal value) {
		this.locale = locale;
		this.currency = currency;
		this.value = value;
	}
	
	@Override
	public String toString() {
		final NumberFormat format = NumberFormat.getInstance(this.locale);
		format.setMinimumFractionDigits(
			this.currency.getDefaultFractionDigits()
		);
		return String.format(
			"%s %s",
			this.currency.getSymbol(this.locale),
			format.format(this.value)
		);
	}
}
