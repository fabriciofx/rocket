package com.github.fabriciofx.rocket.infra.media;

import org.xembly.Directives;
import org.xembly.ImpossibleModificationException;
import org.xembly.Xembler;

public final class XmlMedia implements Media {
	private final transient Directives directives;

	public XmlMedia(final String root) {
		this(new Directives().add(root));
	}

	public XmlMedia(final Directives directives) {
		this.directives = directives;
	}

	@Override
	public Media with(final String name, final String value) {
		return new XmlMedia(directives.add(name.toLowerCase()).set(value).up());
	}

	@Override
	public String toString() {
		try {
			return new Xembler(directives).xml();
		} catch (final ImpossibleModificationException e) {
			throw new IllegalStateException(e);
		}
	}
}
