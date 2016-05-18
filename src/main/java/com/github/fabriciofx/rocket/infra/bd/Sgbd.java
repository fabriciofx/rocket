package com.github.fabriciofx.rocket.infra.bd;

import java.io.IOException;

public interface Sgbd {
	void init() throws IOException;
	
	String url();
}
