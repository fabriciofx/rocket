package com.github.fabriciofx.rocket.misc;

import java.io.Serializable;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;

public final class NotEmptyString
		implements Serializable, Comparable<NotEmptyString>, CharSequence {
	private static final long serialVersionUID = -2845501977163254604L;

	private final String origin;

	public NotEmptyString(final SafeString string) {
		this(string.toString());
	}

	public NotEmptyString(final String string) {
		this.origin = new NotEmpty<String>(new NotNull<>()).valid(string);
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
	public int compareTo(final NotEmptyString o) {
		return origin.compareTo(
			new NotNull<NotEmptyString>().valid(o).origin
		);
	}

	@Override
	public boolean equals(final Object o) {
		return o != null && o instanceof SafeString
			&& origin.equals(NotEmptyString.class.cast(o).origin);
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
