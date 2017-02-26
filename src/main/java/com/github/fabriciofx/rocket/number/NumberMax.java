package com.github.fabriciofx.rocket.number;

import java.util.List;

public final class NumberMax extends Number {
	private static final long serialVersionUID = -1841888983986315443L;
	private final Numbers numbers;
	
	public NumberMax(final Numbers numbers) {
		this.numbers = numbers;
	}
	
	@Override
	public int intValue() {
		return max(this.numbers.all()).intValue();
	}

	@Override
	public long longValue() {
		return max(this.numbers.all()).longValue();
	}

	@Override
	public float floatValue() {
		return max(this.numbers.all()).floatValue();
	}

	@Override
	public double doubleValue() {
		return max(this.numbers.all()).doubleValue();
	}

	private static Number max(final List<Number> all) {
		Number max = all.get(0);
		for (int i = 1; i < all.size(); i++) {
			final Number num = all.get(i);
			if (num.doubleValue() > max.doubleValue()) {
				max = num;
			}
		}
		return max;		
	}
}
