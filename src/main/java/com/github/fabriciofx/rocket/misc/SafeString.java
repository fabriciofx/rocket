package com.github.fabriciofx.rocket.misc;

import java.io.Serializable;

import com.github.fabriciofx.rocket.constraint.NotNull;

public final class SafeString
		implements Serializable, Comparable<SafeString>, CharSequence {
	private static final long serialVersionUID = -6385663348627151869L;

	private final String origin;
	
	public SafeString() {
		this("");
	}
	
	public SafeString(final String string) {
		this.origin = new NotNull<String>().valid(string);
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
	public int compareTo(final SafeString o) {
		return origin.compareTo(o.origin);
	}
	
	@Override
	public boolean equals(final Object o) {
		return o != null && o instanceof SafeString
				&& origin.equals(SafeString.class.cast(o).origin);
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
