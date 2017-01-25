package com.github.fabriciofx.rocket.range;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import com.github.fabriciofx.rocket.as.StringAsDate;
import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.media.Media;

public final class RangeDate implements Range<ChronoLocalDate> {
	private final LocalDate begin;
	private final LocalDate end;

	public RangeDate(final StringAsDate begin, final StringAsDate end) {
		this(begin.date(), end.date());
	}
	
	public RangeDate(final LocalDate begin, final LocalDate end) {
		this.begin = begin;
		this.end = end;
	}

	@Override
	public boolean contains(final ChronoLocalDate date) {
		if (new NotNull<LocalDate>()
			.valid(begin)
			.isAfter(new NotNull<LocalDate>().valid(end))) {
			throw new IllegalArgumentException(
				String.format("'%s' must be before '%s'", begin, end)
			);
		}
		return begin.compareTo(date) <= 0 && end.compareTo(date) >= 0;
	}

	@Override
	public String toString() {
		final DateTimeFormatter format = DateTimeFormatter
				.ofLocalizedDate(FormatStyle.SHORT);
		return String.format(
			"%s to %s",
			begin.format(format),
			end.format(format)
		);
	}

	@Override
	public Media about(final Media media) {
		final DateTimeFormatter format = DateTimeFormatter
				.ofLocalizedDate(FormatStyle.SHORT);
		return media
			.with("begin", begin.format(format))
			.with("end",end.format(format));
	}
}
