package com.github.fabriciofx.rocket.name;

import com.github.fabriciofx.rocket.text.Text;
import com.github.fabriciofx.rocket.text.TextCleaned;
import com.github.fabriciofx.rocket.text.TextSplited;

public final class NameEnglish implements Text {
	private final NameFirstLast origin;
	
	public NameEnglish(final String content) {
		this(new Name(content));
	}
	
	public NameEnglish(final Name origin) {
		this(
			new NameFirstLast(
				new TextSplited(
					new TextCleaned(
						origin
					)
				)
			)
		);		
	}
	
	public NameEnglish(final NameFirstLast origin) {
		this.origin = origin;
	}
	
	@Override
	public String content() {
		return String.format(
			"%s %s",
			this.origin.first(),
			this.origin.last()
		);
	}
}
