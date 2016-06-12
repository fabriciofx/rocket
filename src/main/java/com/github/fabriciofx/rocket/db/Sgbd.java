package com.github.fabriciofx.rocket.db;

import java.io.IOException;

public interface Sgbd {
	void init() throws IOException;
	
	String url();
}
