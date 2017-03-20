package com.github.fabriciofx.rocket.text;

public final class TextSimple implements Text {
	private final String content;
	
	public TextSimple(final String content) {
		this.content = content;
	}

	@Override
	public String content() {
		return this.content;
	}
}
