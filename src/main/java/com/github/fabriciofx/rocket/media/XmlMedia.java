package com.github.fabriciofx.rocket.media;

import org.xembly.Directives;
import org.xembly.ImpossibleModificationException;
import org.xembly.Xembler;

public final class XmlMedia implements Media {
	private final String root;
	private final Directives directives;

	public XmlMedia(final String root) {
		this(root, new Directives().add(root));
	}

	public XmlMedia(final String root, final Directives directives) {
		this.root = root;
		this.directives = directives;
	}

	@Override
	public Media with(final String name, final Object value) {
		return new XmlMedia(root,
			directives.add(name.toLowerCase()).set(value.toString()).up());
	}
	
	@Override
	public String toString() {
		try {
			return new Xembler(directives)
				.xml()
				.replaceAll("<\\?xml version.*\\?>[\r]*\n", "")
				.replaceAll("&lt;", "<")
				.replaceAll("&gt;", ">")
				.replaceAll("&#13;", "");
		} catch (final ImpossibleModificationException e) {
			throw new IllegalStateException(e);
		}
	}
}
