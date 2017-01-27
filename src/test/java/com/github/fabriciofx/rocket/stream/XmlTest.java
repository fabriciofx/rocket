package com.github.fabriciofx.rocket.stream;

import java.io.IOException;

import org.w3c.dom.Element;

public final class XmlTest {
	public static void main(String[] args) throws IOException {
		final StreamXml doc = new StreamXml();
		final Element company = doc.element("company");
		final Element staff = doc.element(company, "staff");
		doc.element(staff, "firstname", "Steve");
		
		doc.write(System.out);
	}
}
