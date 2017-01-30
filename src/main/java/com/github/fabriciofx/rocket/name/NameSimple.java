package com.github.fabriciofx.rocket.name;

public final class NameSimple implements Name {
	private final String content;
	
	public NameSimple(final String content) {
		this.content = content;
	}

	@Override
	public String content() {
		return content;
	}
}
