package com.github.fabriciofx.rocket.range;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.media.Media;

public final class RangeDateTime implements Range<ChronoLocalDateTime<?>> {
	private final LocalDateTime begin;
	private final LocalDateTime end;

	public RangeDateTime(final LocalDateTime begin, final LocalDateTime end) {
		this.begin = begin;
		this.end = end;
	}

	@Override
	public boolean contains(final ChronoLocalDateTime<?> datetime) {
		if (new NotNull<LocalDateTime>()
			.valid(begin)
			.isAfter(new NotNull<LocalDateTime>().valid(end))) {
				throw new IllegalArgumentException(
					String.format("'%s' must be before '%s'", begin, end)
			);
		}
		return begin.compareTo(datetime) <= 0 && end.compareTo(datetime) >= 0;
	}

	@Override
	public String toString() {
		final DateTimeFormatter format = DateTimeFormatter
			.ofLocalizedDateTime(FormatStyle.SHORT);
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
			.with("end", end.format(format));
	}
}
