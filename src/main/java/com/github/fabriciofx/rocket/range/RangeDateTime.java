package com.github.fabriciofx.rocket.range;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.media.Media;
import com.github.fabriciofx.rocket.media.XmlMedia;
import com.github.fabriciofx.rocket.string.StringAsDatetime;

public final class RangeDateTime implements Range<ChronoLocalDateTime<?>> {
	private final LocalDateTime begin;
	private final LocalDateTime end;
	private final Media media;
	
	public RangeDateTime(final StringAsDatetime begin,
		final StringAsDatetime end) {
		this(begin.dateTime(), end.dateTime());
	}

	public RangeDateTime(final LocalDateTime begin, final LocalDateTime end) {
		this(begin, end, new XmlMedia("range-datetime"));
	}

	public RangeDateTime(final LocalDateTime begin, final LocalDateTime end,
		final Media media) {
		this.begin = begin;
		this.end = end;
		this.media = media;
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
	public Media about() {
		final DateTimeFormatter format = DateTimeFormatter
			.ofLocalizedDate(FormatStyle.SHORT);
		return media
			.with("begin", begin.format(format))
			.with("end", end.format(format));
	}
}
