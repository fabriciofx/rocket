package com.github.fabriciofx.rocket.name;

public final class NameUpped implements Name {
	private final Name origin;
	
	public NameUpped(final Name origin) {
		this.origin = origin;
	}
	
	@Override
	public String content() {
		return this.origin.content().toUpperCase();
	}
}
