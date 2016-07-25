package com.github.fabriciofx.rocket.system;

import java.io.IOException;

public interface User<T> {
	T name() throws IOException;

	Password password() throws IOException;
}
