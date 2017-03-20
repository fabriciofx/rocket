package com.github.fabriciofx.rocket.name;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.fabriciofx.rocket.text.Text;
import com.github.fabriciofx.rocket.text.TextCleaned;
import com.github.fabriciofx.rocket.text.TextSafe;
import com.github.fabriciofx.rocket.text.TextSplited;
import com.github.fabriciofx.rocket.text.TextUpped;

public class NameTest {
	@Test
	public void cleaned() {
		final String content = "  Fabricio   Barros Cabral		";
		final Text name = new TextCleaned(
			new Name(content)
		);
		assertEquals("Fabricio Barros Cabral", name.content());
	}
	
	@Test
	public void abbreviated() {
		final String content = "  Fabricio   Barros Cabral		";
		final Text name = new NameAbbreviated(
			new TextSplited(
				new TextCleaned(
					new Name(content)
				)
			)
		);
		assertEquals("Fabricio B. Cabral", name.content());		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void notNull() {
		new TextSafe(
			new Name(null)
		).content();
	}

	@Test(expected = IllegalArgumentException.class)
	public void notEmpty() {
		new TextSafe(
			new Name("")
		).content();
	}

	@Test
	public void english() {
		final String content = "  Fabricio   Barros Cabral		";
		final Text name = new NameEnglish(
			new NameFirstLast(
				new TextSplited(
					new TextCleaned(
						new Name(content)
					)
				)
			)
		);
		assertEquals("Fabricio Cabral", name.content());
	}

	@Test
	public void upped() {
		final String content = "  Fabricio   Barros Cabral		";
		final Text name = new TextUpped( 
			new NameEnglish(
				new NameFirstLast(
					new TextSplited(
						new TextCleaned(
							new Name(content)
						)
					)
				)
			)
		);
		assertEquals("FABRICIO CABRAL", name.content());
	}
}
