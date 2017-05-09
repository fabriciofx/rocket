package com.github.fabriciofx.rocket.email;

public final class ExceptionInvalidEmail extends IllegalArgumentException {
	private static final long serialVersionUID = -7117866771151276141L;
	
	public ExceptionInvalidEmail(final String email) {
		super(String.format("'%s' is a invalid e-mail address", email));
	}
}
