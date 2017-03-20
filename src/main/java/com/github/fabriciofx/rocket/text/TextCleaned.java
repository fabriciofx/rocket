package com.github.fabriciofx.rocket.text;

public final class TextCleaned implements Text {
	private final Text origin;
	
	public TextCleaned(final Text origin) {
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