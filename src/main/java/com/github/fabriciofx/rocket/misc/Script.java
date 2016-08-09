package com.github.fabriciofx.rocket.misc;

import java.io.IOException;

public interface Script<Context> {
	void exec(Context context) throws IOException;
}
