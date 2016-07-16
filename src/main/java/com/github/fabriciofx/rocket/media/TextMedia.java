package com.github.fabriciofx.rocket.media;

public final class TextMedia implements Media {
	private final transient StringBuilder sb;

	public TextMedia() {
		this(new StringBuilder());
	}

	public TextMedia(final StringBuilder sb) {
		this.sb = sb;
	}

	@Override
	public Media with(final String name, final String value) {
		return new TextMedia(sb.append(name).append(": ").append(value)
				.append(System.lineSeparator()));
	}

	@Override
	public String toString() {
		return sb.toString();
	}
}
