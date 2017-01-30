package com.github.fabriciofx.rocket.name;

public final class NameSafe implements Name {
	private final Name origin;
	
	public NameSafe(final Name origin) {
		this.origin = origin;
	}
	
	@Override
	public String content() {
		if (origin.content() == null || origin.content().isEmpty()) {
			throw new IllegalArgumentException("invalid name");
		}
		return origin.content();
	}
}
