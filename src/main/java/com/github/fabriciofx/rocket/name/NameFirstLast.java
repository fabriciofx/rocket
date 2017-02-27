package com.github.fabriciofx.rocket.name;

public final class NameFirstLast implements Name {
	private final NameSplited origin;
	
	public NameFirstLast(final NameSplited origin) {
		this.origin = origin;
	}
	
	@Override
	public String content() {
		return this.origin.content();
	}
	
	public String first() {
		return this.origin.parts().get(0);
	}
	
	public String last() {
		return this.origin.parts().get(this.origin.parts().size() - 1);
	}
}
