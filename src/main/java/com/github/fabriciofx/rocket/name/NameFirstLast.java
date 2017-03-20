package com.github.fabriciofx.rocket.name;

import com.github.fabriciofx.rocket.text.Text;
import com.github.fabriciofx.rocket.text.TextSplited;

public final class NameFirstLast implements Text {
	private final TextSplited origin;
	
	public NameFirstLast(final TextSplited origin) {
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
