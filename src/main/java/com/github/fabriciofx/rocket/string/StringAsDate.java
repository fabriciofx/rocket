package com.github.fabriciofx.rocket.string;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class StringAsDate {
	private final CharSequence date;
	private final String pattern;

	public StringAsDate(final CharSequence date) {
		this(date, "dd/MM/yyyy");
	}
	
	public StringAsDate(final CharSequence date, final String pattern) {
		this.date = date;
		this.pattern = pattern;
	}
	
	public LocalDate date() {
		return LocalDate.parse(date,
			DateTimeFormatter.ofPattern(pattern)
		);
	}
}
