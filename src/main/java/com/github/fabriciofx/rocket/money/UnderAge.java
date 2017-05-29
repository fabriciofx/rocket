package com.github.fabriciofx.rocket.money;

import java.time.LocalDate;
import java.time.Period;

public final class UnderAge implements Conditional {
	private final LocalDate birthday;
	
	public UnderAge(final LocalDate birthday) {
		this.birthday = birthday;
	}
	
	@Override
	public boolean evaluation() {
		final boolean value;
		if (Period.between(this.birthday, LocalDate.now()).getYears() < 18) {
			value = true;
		} else {
			value = false;
		}
		return value;
	}
}
