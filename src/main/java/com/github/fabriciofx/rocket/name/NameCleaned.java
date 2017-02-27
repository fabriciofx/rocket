package com.github.fabriciofx.rocket.name;

public final class NameCleaned implements Name {
	private final Name origin;
	
	public NameCleaned(final Name origin) {
		this.origin = origin;
	}
	
	@Override
	public String content() {
		return this.origin
			.content()
			.replaceAll(
				"^\\s+|\\s+$|\\s*(" +
				System.lineSeparator() +
				")\\s*|(\\s)\\s*", "$1$2"
			)
			.replaceAll("[\\t\\f]+", " ");
	}
}