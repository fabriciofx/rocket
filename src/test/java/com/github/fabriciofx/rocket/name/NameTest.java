package com.github.fabriciofx.rocket.name;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NameTest {
	@Test
	public void cleaned() {
		final String content = "  Fabricio   Barros Cabral		";
		final Name name = new NameCleaned(
			new NameSimple(content)
		);
		assertEquals("Fabricio Barros Cabral", name.content());
	}
	
	@Test
	public void abbreviated() {
		final String content = "  Fabricio   Barros Cabral		";
		final Name name = new NameAbbreviated(
			new NameSplited(
				new NameCleaned(
					new NameSimple(content)
				)
			)
		);
		assertEquals("Fabricio B. Cabral", name.content());		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void notNull() {
		new NameSafe(
			new NameSimple(null)
		).content();
	}

	@Test(expected = IllegalArgumentException.class)
	public void noEmpty() {
		new NameSafe(
			new NameSimple("")
		).content();
	}

	@Test
	public void english() {
		final String content = "  Fabricio   Barros Cabral		";
		final Name name = new NameEnglish(
			new NameFirstLast(
				new NameSplited(
					new NameCleaned(
						new NameSimple(content)
					)
				)
			)
		);
		assertEquals("Fabricio Cabral", name.content());
	}

	@Test
	public void upped() {
		final String content = "  Fabricio   Barros Cabral		";
		final Name name = new NameUpped( 
			new NameEnglish(
				new NameFirstLast(
					new NameSplited(
						new NameCleaned(
							new NameSimple(content)
						)
					)
				)
			)
		);
		assertEquals("FABRICIO CABRAL", name.content());
	}
}
