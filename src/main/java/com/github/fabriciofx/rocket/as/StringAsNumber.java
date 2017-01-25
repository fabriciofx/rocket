package com.github.fabriciofx.rocket.as;

public final class StringAsNumber extends Number {
	private static final long serialVersionUID = 356954227922735088L;
	private final String origin;
	
	public StringAsNumber(final String origin) {
		this.origin = origin;
	}

	@Override
	public int intValue() {
		return Integer.parseInt(origin);
	}

	@Override
	public long longValue() {
		return Long.parseLong(origin);
	}

	@Override
	public float floatValue() {
		return Float.parseFloat(origin);
	}

	@Override
	public double doubleValue() {
		return Double.parseDouble(origin);
	}
}
