package com.github.fabriciofx.rocket.string;

import java.io.Serializable;

public final class StringSafe
		implements Serializable, Comparable<StringSafe>, CharSequence {
	private static final long serialVersionUID = -6385663348627151869L;
	private final String origin;
	
	public StringSafe(final String origin) {
		this.origin = origin;
	}
	
	@Override
	public int length() {
		if (this.origin == null) {
			throw new NullPointerException("the origin string is null");
		}
		return this.origin.length();
	}

	@Override
	public char charAt(final int index) {
		if (this.origin == null) {
			throw new NullPointerException("the origin string is null");
		}
		return this.origin.charAt(index);
	}

	@Override
	public CharSequence subSequence(final int start, final int end) {
		if (this.origin == null) {
			throw new NullPointerException("the origin string is null");
		}
		return this.origin.subSequence(start, end);
	}

	@Override
	public int compareTo(final StringSafe o) {
		if (this.origin == null) {
			throw new NullPointerException("the origin string is null");
		}
		return this.origin.compareTo(o.origin);
	}
	
	@Override
	public boolean equals(final Object o) {
		return o != null &&
			o instanceof StringSafe &&
			this.origin.equals(StringSafe.class.cast(o).origin);
	}

	@Override
	public final int hashCode() {
		return 31 + this.origin.hashCode();
	}

	@Override
	public String toString() {
		return this.origin.toString();
	}	
}
