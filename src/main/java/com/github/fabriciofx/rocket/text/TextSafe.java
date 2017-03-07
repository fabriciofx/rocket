package com.github.fabriciofx.rocket.text;

public final class TextSafe implements Text {
	private final Text origin;
	
	public TextSafe(final Text origin) {
		this.origin = origin;
	}
	
	@Override
	public String content() {
		if (this.origin == null || this.origin.content() == null ||
			this.origin.content().isEmpty()) {
			throw new IllegalArgumentException("invalid text (null or empty)");
		}
		return this.origin.content();
	}
}
