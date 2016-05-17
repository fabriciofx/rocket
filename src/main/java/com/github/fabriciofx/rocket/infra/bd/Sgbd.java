package com.github.fabriciofx.rocket.infra.bd;

public interface Sgbd {
	String driver();
	
	String host();

	int porta();
	
	String url(String banco);
}
