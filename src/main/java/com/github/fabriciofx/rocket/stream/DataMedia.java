package com.github.fabriciofx.rocket.stream;

import java.io.IOException;

public interface DataMedia {
	StreamData stream() throws IOException;
	
	String string();
}
