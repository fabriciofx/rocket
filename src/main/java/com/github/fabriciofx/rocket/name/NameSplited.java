package com.github.fabriciofx.rocket.name;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class NameSplited implements Name {
	private final Name origin;
	
	public NameSplited(final Name origin) {
		this.origin = origin;
	}
	
	@Override
	public String content() {
		return origin.content();
	}
	
	public List<String> parts() {
		return Collections.unmodifiableList(
			Arrays.asList(
				origin.content().split(" ")
			)
		);		
	}
}
