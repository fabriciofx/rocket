package com.github.fabriciofx.rocket.email;

import java.util.regex.Pattern;

public final class EmailSafe implements Email {
	private final static String EMAIL_PATTERN =
		"^[\\w\\-]+(\\.[\\w\\-]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$";
	private final Email origin;
	private final Pattern pattern;
	
	public EmailSafe(final Email origin) {
		this(origin, Pattern.compile(EmailSafe.EMAIL_PATTERN));
	}
	
	public EmailSafe(final Email origin, final Pattern pattern) {
		this.origin = origin;
		this.pattern = pattern;
	}

	@Override
	public String address() {
		final String address = this.origin.address();
		if (address == null ||
			address.isEmpty() ||
			!this.pattern.matcher(address).matches()
		) {
			throw new EmailInvalidException(address);
		}
		return address;
	}
}
