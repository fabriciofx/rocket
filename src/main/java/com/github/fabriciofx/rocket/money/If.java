package com.github.fabriciofx.rocket.money;

public final class If<T> implements Scalar<T> {
	private final Conditional conditional;
	private final T a;
	private final T b;

	public If(final Conditional conditional, final T a, final T b) {
		this.conditional = conditional;
		this.a = a;
		this.b = b;
	}

	@Override
	public T asValue() {
		final T value;
		if (this.conditional.evaluation()) {
			value = this.a;
		} else {
			value = this.b;
		}
		return value;
	}
}
