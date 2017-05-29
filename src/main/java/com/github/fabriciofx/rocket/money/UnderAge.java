package com.github.fabriciofx.rocket.money;

import java.time.LocalDate;
import java.time.Period;

public final class UnderAge implements Conditional {
	private final LocalDate birthday;
	private final int age;

	public UnderAge(final LocalDate birthday) {
		this(birthday, 18);
	}
	
	public UnderAge(final LocalDate birthday, final int age) {
		this.birthday = birthday;
		this.age = age;
	}
	
	@Override
	public boolean evaluation() {
		final boolean value;
		if (Period.between(
				this.birthday,
				LocalDate.now()
			).getYears() < this.age) {
			value = true;
		} else {
			value = false;
		}
		return value;
	}
}
