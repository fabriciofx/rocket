package com.github.fabriciofx.rocket.range;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.media.Media;

public final class DateRange implements Range<ChronoLocalDate> {
	private final transient LocalDate begin;
	private final transient LocalDate end;

	public DateRange(final LocalDate begin, final LocalDate end) {
		this.begin = new NotNull<LocalDate>().valid(begin);
		this.end = new NotNull<LocalDate>().valid(end);
		if (this.begin.isAfter(this.end)) {
			throw new IllegalArgumentException(
				String.format("'%s' must be before '%s'", begin, end)
			);
		}
	}

	@Override
	public boolean contains(final ChronoLocalDate date) {
		return begin.compareTo(date) <= 0 && end.compareTo(date) >= 0;
	}

	@Override
	public String toString() {
		final DateTimeFormatter format = DateTimeFormatter
				.ofLocalizedDate(FormatStyle.SHORT);
		return String.format("%s to %s", begin.format(format),
				end.format(format));
	}

	@Override
	public Media print(final Media media) {
		final DateTimeFormatter format = DateTimeFormatter
				.ofLocalizedDate(FormatStyle.SHORT);
		return media.with("begin", begin.format(format)).with("end",
				end.format(format));
	}
}
