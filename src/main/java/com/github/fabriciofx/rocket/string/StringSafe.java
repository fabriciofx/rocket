package com.github.fabriciofx.rocket.string;

import java.io.Serializable;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;

public final class StringSafe
		implements Serializable, Comparable<StringSafe>, CharSequence {
	private static final long serialVersionUID = -6385663348627151869L;
	private final String origin;
	
	public StringSafe(final String origin) {
		this.origin = origin;
	}
	
	@Override
	public int length() {
		return new NotEmpty<String>(
			new NotNull<>()
		).valid(origin).length();
	}

	@Override
	public char charAt(final int index) {
		return new NotEmpty<String>(
			new NotNull<>()
		).valid(origin).charAt(index);
	}

	@Override
	public CharSequence subSequence(final int start, final int end) {
		return new NotEmpty<String>(
			new NotNull<>()
		).valid(origin).subSequence(start, end);
	}

	@Override
	public int compareTo(final StringSafe o) {
		return new NotEmpty<String>(
			new NotNull<>()
		).valid(origin).compareTo(o.origin);
	}
	
	@Override
	public boolean equals(final Object o) {
		return o != null &&
			o instanceof StringSafe &&
			origin.equals(StringSafe.class.cast(o).origin);
	}

	@Override
	public final int hashCode() {
		return 31 + origin.hashCode();
	}

	@Override
	public String toString() {
		return origin.toString();
	}	
}
