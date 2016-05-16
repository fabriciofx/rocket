package com.github.fabriciofx.rocket.infra.bd;

public interface Sgbd {
	String driver();

	int porta();
	
	String url(String banco);
}
