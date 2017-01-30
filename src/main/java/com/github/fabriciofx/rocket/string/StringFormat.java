package com.github.fabriciofx.rocket.string;

public final class StringFormat {
	private final String format;
	private final Object[] args;

	public StringFormat(final String format, final Object... args) {
		this.format = format;
		this.args = args;
	}

	@Override
	public String toString() {
		return String.format(format, args);
	}
}
