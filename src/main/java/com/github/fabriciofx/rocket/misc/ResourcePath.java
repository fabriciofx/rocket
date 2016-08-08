package com.github.fabriciofx.rocket.misc;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public final class ResourcePath {
	private final transient Class<?> resource;
	private final transient List<String> files;
	
	public ResourcePath(final String... files) {
		this(ResourcePath.class, files);
	}
	
	public ResourcePath(final Class<?> resource, final String... files) {
		this(resource, Arrays.asList(files));
	}
	
	public ResourcePath(final Class<?> resource, final List<String> files) {
		this.resource = resource;
		this.files = new ArrayList<>(files);
	}
	
	public URI uri() {
		final StringJoiner sj = new StringJoiner(File.separator);
		for (final String f : files) {
			sj.add(f);
		}
		try {
			return resource
				.getClassLoader()
				.getResource(sj.toString())
				.toURI();
		} catch (final URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}
}
