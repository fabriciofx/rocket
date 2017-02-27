package com.github.fabriciofx.rocket.email;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class EmailSimple implements Email {
	private final String address;

	public EmailSimple(final String address) {
		this.address = address; 
	}

	@Override
	public String address() {
		return this.address;
	}
}
