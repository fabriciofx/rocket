package com.github.fabriciofx.rocket.text;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class TextSplited implements Text {
	private final Text origin;
	private final String regex;
	
	public TextSplited(final Text origin) {
		this(origin, " ");
	}
	
	public TextSplited(final Text origin, final String regex) {
		this.origin = origin;
		this.regex = regex;
	}
	
	@Override
	public String content() {
		return this.origin.content();
	}
	
	public List<String> parts() {
		return Collections.unmodifiableList(
			Arrays.asList(
				origin.content().split(regex)
			)
		);		
	}
}
