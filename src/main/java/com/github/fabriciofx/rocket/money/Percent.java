package com.github.fabriciofx.rocket.money;

public final class Percent implements Scalar<Double> {
	private final double value;
	
	public Percent(final double value) {
		this.value = value;
	}
	
	@Override
	public Double asValue() {
		return this.value;
	}
}
