package com.github.fabriciofx.rocket.misc;

import java.io.Serializable;
import java.nio.charset.Charset;

import com.github.fabriciofx.rocket.constraint.NotNull;

public final class Utf8String
		implements Serializable, Comparable<Utf8String>, CharSequence {
	private static final long serialVersionUID = 4708199753825765959L;

	private static final String ENCODING = "UTF-8";
	private final String origin;

	public Utf8String(final byte... bytes) {
		this(new String(bytes, Charset.forName(Utf8String.ENCODING)));
	}

	public Utf8String(final String string) {
		this.origin = string;
	}

	public byte[] bytes() {
		return origin.getBytes(Charset.forName(Utf8String.ENCODING));
	}

	@Override
	public boolean equals(final Object o) {
		return o != null && o instanceof Utf8String
				&& origin.equals(Utf8String.class.cast(o).origin);
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
	public int compareTo(final Utf8String o) {
		return origin.compareTo(
			new NotNull<Utf8String>().valid(o).origin
		);
	}
}
