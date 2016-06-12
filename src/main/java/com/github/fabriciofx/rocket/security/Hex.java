package com.github.fabriciofx.rocket.security;

import java.util.Arrays;

public final class Hex {
	private static final char[] HEX_CHARS = {
		'0', '1', '2', '3', '4', '5', '6', '7',
		'8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
	};
	
	private final transient byte[] data;
	
	public Hex(final byte[] data) {
		this.data = Arrays.copyOf(data, data.length);
	}
	
	@Override
	public String toString() {
		final char[] hex = new char[data.length * 2];
		for (int c = 0, i = 0; i < data.length; i++) {
			final int v = 0xFF & data[i];
			hex[c++] = HEX_CHARS[v >>> 4];
			hex[c++] = HEX_CHARS[v & 0x0F];
		}
		return new String(hex);
	}
}
