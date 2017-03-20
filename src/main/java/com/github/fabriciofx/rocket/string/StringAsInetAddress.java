package com.github.fabriciofx.rocket.string;

import java.net.InetAddress;
import java.net.UnknownHostException;

public final class StringAsInetAddress {
	private final String origin;
	
	public StringAsInetAddress(final String origin) {
		this.origin = origin;
	}
	
	public InetAddress address() throws UnknownHostException {
		return InetAddress.getByName(this.origin);
	}
}
