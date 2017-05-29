package com.github.fabriciofx.rocket.money;

import java.math.BigDecimal;
import java.time.LocalDate;

public final class MoneyDiscountRate implements Money {
	private final Money origin;

	public MoneyDiscountRate(final Money origin, final LocalDate birthday) {
		this(
			new MoneyPercentCalculated(
				origin,
				new If<Double>(
					new UnderAge(birthday),
					10.0,
					0.0
				)
			)
		);
	}

	public MoneyDiscountRate(final Money origin) {
		this.origin = origin;
	}
	
	@Override
	public BigDecimal value() {
		return this.origin.value();
	}
	
	@Override
	public String toString() {
		return this.origin.toString();
	}
}
