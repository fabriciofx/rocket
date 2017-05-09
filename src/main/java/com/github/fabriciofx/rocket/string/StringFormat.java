package com.github.fabriciofx.rocket.string;

import java.io.Serializable;

public final class StringFormat
	implements Serializable, Comparable<StringFormat>, CharSequence {
	private static final long serialVersionUID = 5196167298744019069L;
	private final String format;
	private final Object[] args;

	public StringFormat(final String format, final Object... args) {
		this.format = format;
		this.args = args;
	}

	@Override
	public String toString() {
		return String.format(this.format, this.args);
	}

	@Override
	public int length() {
		return this.toString().length();
	}

	@Override
	public char charAt(final int index) {
		return this.toString().charAt(index);
	}

	@Override
	public CharSequence subSequence(final int start, final int end) {
		return this.toString().subSequence(start, end);
	}

	@Override
	public int compareTo(final StringFormat o) {
		return this.toString().compareTo(o.toString());
	}
}
