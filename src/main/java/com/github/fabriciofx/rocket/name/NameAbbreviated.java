package com.github.fabriciofx.rocket.name;

import com.github.fabriciofx.rocket.text.Text;
import com.github.fabriciofx.rocket.text.TextSplited;

public final class NameAbbreviated implements Text {
	private final TextSplited origin;
	
	public NameAbbreviated(final TextSplited origin) {
		this.origin = origin;
	}
	
	@Override
	public String content() {
		final StringBuilder sb = new StringBuilder();
		final int last = this.origin.parts().size() - 1;
		sb.append(this.origin.parts().get(0)).append(" ");
		for (int i = 1; i < last; i++) {
			sb.append(
				Character.toUpperCase(
					this.origin.parts().get(i).charAt(0)
				)
			)
			.append(". ");
		}
		sb.append(this.origin.parts().get(last));
		return sb.toString();
	}
}
