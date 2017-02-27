package com.github.fabriciofx.rocket.name;

public final class NameSafe implements Name {
	private final Name origin;
	
	public NameSafe(final Name origin) {
		this.origin = origin;
	}
	
	@Override
	public String content() {
		if (this.origin == null || this.origin.content() == null ||
			this.origin.content().isEmpty()) {
			throw new IllegalArgumentException("invalid name (null or empty)");
		}
		return this.origin.content();
	}
}
