package com.github.fabriciofx.rocket.name;

public final class NameEnglish implements Name {
	private final NameFirstLast origin;
	
	public NameEnglish(final String content) {
		this(new NameSimple(content));
	}
	
	public NameEnglish(final Name origin) {
		this(
			new NameFirstLast(
				new NameSplited(
					new NameCleaned(
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
