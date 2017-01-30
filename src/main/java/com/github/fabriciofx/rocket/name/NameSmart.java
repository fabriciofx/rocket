package com.github.fabriciofx.rocket.name;

public final class NameSmart implements Name {
	private final String content;
	
	public NameSmart(final String content) {
		this.content = content;
	}

	@Override
	public String content() {
		return content;
	}
}
