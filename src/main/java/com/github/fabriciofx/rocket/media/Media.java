package com.github.fabriciofx.rocket.media;

public interface Media {
	Media with(String name, String value);
	
	Media dup();
}
