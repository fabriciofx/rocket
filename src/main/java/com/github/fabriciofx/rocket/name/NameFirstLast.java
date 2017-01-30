package com.github.fabriciofx.rocket.name;

public final class NameFirstLast implements Name {
	private final NameSplited origin;
	
	public NameFirstLast(final NameSplited origin) {
		this.origin = origin;
	}
	
	@Override
	public String content() {
		return origin.content();
	}
	
	public String first() {
		return origin.parts().get(0);
	}
	
	public String last() {
		return origin.parts().get(origin.parts().size() - 1);
	}
}
