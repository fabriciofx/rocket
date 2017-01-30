package com.github.fabriciofx.rocket.string;

import java.io.Serializable;
import java.nio.charset.Charset;

public final class StringUtf8
		implements Serializable, Comparable<StringUtf8>, CharSequence {
	private static final long serialVersionUID = 4708199753825765959L;

	private static final String ENCODING = "UTF-8";
	private final String origin;

	public StringUtf8(final byte... bytes) {
		this(
			new String(
				bytes,
				Charset.forName(StringUtf8.ENCODING)
			)
		);
	}

	public StringUtf8(final String string) {
		this.origin = string;
	}

	public byte[] bytes() {
		return origin.getBytes(
			Charset.forName(StringUtf8.ENCODING)
		);
	}

	@Override
	public boolean equals(final Object o) {
		return o != null &&
			o instanceof StringUtf8 &&
			origin.equals(StringUtf8.class.cast(o).origin);
	}

	@Override
	public final int hashCode() {
		return 31 + origin.hashCode();
	}

	@Override
	public String toString() {
		return origin.toString();
	}

	@Override
	public int length() {
		return origin.length();
	}

	@Override
	public char charAt(final int index) {
		return origin.charAt(index);
	}

	@Override
	public CharSequence subSequence(final int start, final int end) {
		return origin.subSequence(start, end);
	}

	@Override
	public int compareTo(final StringUtf8 o) {
		return origin.compareTo(o.origin);
	}
}
