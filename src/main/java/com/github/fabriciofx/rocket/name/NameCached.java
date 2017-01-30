package com.github.fabriciofx.rocket.name;

public final class NameCached implements Name {
	private final String content;
	
	public NameCached(final Name origin) {
		this.content = origin.content();
	}
	
	@Override
	public String content() {
		return content;
	}
}
