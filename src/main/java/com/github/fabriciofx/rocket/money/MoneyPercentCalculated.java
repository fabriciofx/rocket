package com.github.fabriciofx.rocket.money;

import java.math.BigDecimal;

public final class MoneyPercentCalculated implements Money {
	private final Money origin;
	private final Scalar<Double> percent;
	
	public MoneyPercentCalculated(final Money origin,
		final Scalar<Double> percent) {
		this.origin = origin;
		this.percent = percent;
	}
	
	@Override
	public BigDecimal value() {
		final double p = this.percent.asValue();
		final BigDecimal value;
		if (p > 0) {
			value = this.origin.value()
				.multiply(new BigDecimal(p))
				.divide(new BigDecimal(100.0));
		} else {
			value = this.origin.value();
		}
		return value;
	}
	
	@Override
	public String toString() {
		return new MoneySmart(this.value()).toString();
	}
}
