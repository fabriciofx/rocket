package com.github.fabriciofx.rocket.text;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class TextSplited implements Text {
	private final Text origin;
	
	public TextSplited(final Text origin) {
		this.origin = origin;
	}
	
	@Override
	public String content() {
		return this.origin.content();
	}
	
	public List<String> parts() {
		return Collections.unmodifiableList(
			Arrays.asList(
				origin.content().split(" ")
			)
		);		
	}
}
