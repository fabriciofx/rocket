package com.github.fabriciofx.rocket.name;

import com.github.fabriciofx.rocket.text.Text;

public final class Name implements Text {
	private final String content;
	
	public Name(final String content) {
		this.content = content;
	}

	@Override
	public String content() {
		return this.content;
	}
}
