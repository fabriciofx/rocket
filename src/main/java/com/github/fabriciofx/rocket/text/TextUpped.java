package com.github.fabriciofx.rocket.text;

public final class TextUpped implements Text {
	private final Text origin;
	
	public TextUpped(final Text origin) {
		this.origin = origin;
	}
	
	@Override
	public String content() {
		return this.origin.content().toUpperCase();
	}
}
