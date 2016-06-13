package com.github.fabriciofx.rocket.db;

import java.io.IOException;

public interface Dbms {
	void init() throws IOException;
	
	String url();
}
