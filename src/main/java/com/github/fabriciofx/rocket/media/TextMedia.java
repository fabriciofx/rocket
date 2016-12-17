package com.github.fabriciofx.rocket.media;

public final class TextMedia implements Media {
	private final StringBuilder sb;

	public TextMedia() {
		this(new StringBuilder());
	}

	public TextMedia(final StringBuilder sb) {
		this.sb = sb;
	}

	@Override
	public Media with(final String name, final Object value) {
		return new TextMedia(
			sb.append(name).append(": ")
			.append(value.toString()).append(System.lineSeparator())
		);
	}
}
