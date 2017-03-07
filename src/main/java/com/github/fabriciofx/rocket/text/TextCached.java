package com.github.fabriciofx.rocket.text;

public final class TextCached implements Text {
	private final String content;
	
	public TextCached(final Text origin) {
		this.content = origin.content();
	}
	
	@Override
	public String content() {
		return content;
	}
}
