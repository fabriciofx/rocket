package com.github.fabriciofx.rocket.string;

import java.io.Serializable;
import java.nio.charset.Charset;

public final class StringUtf8
		implements Serializable, Comparable<StringUtf8>, CharSequence {
	private static final long serialVersionUID = 4708199753825765959L;
	private final String origin;

	public StringUtf8(final byte... bytes) {
		this(
			new String(
				bytes,
				Charset.forName("UTF-8")
			)
		);
	}

	public StringUtf8(final String string) {
		this.origin = string;
	}

	public byte[] bytes() {
		return this.origin.getBytes(
			Charset.forName("UTF-8")
		);
	}

	@Override
	public boolean equals(final Object o) {
		return o != null &&
			o instanceof StringUtf8 &&
			this.origin.equals(StringUtf8.class.cast(o).origin);
	}

	@Override
	public final int hashCode() {
		return 31 + this.origin.hashCode();
	}

	@Override
	public String toString() {
		return this.origin.toString();
	}

	@Override
	public int length() {
		return this.origin.length();
	}

	@Override
	public char charAt(final int index) {
		return this.origin.charAt(index);
	}

	@Override
	public CharSequence subSequence(final int start, final int end) {
		return this.origin.subSequence(start, end);
	}

	@Override
	public int compareTo(final StringUtf8 o) {
		return this.origin.compareTo(o.origin);
	}
}
