package com.github.fabriciofx.rocket.constraint;

public final class Email<T extends CharSequence> implements Constraint<T> {
	private final static String EMAIL_REGEX =
			"^[\\w\\-]+(\\.[\\w\\-]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$";
	private final NotEmpty<T> origin;
	
	public Email(final NotEmpty<T> origin) {
		this.origin = origin;
	}

	@Override
	public T valid(final T object) {
		origin.valid(object);
		if (!object.toString().matches(EMAIL_REGEX)) {
			throw new IllegalArgumentException(
				String.format("'%s' is a invalid e-mail address", object)
			);
		}
		return object;
	}
}
