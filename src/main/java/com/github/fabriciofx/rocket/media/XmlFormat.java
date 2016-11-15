package com.github.fabriciofx.rocket.media;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public final class XmlFormat {
	private final String xml;
	private final int indent;

	public XmlFormat(final Media media) {
		this(media.toString());
	}

	public XmlFormat(final String xml) {
		this(xml, 2);
	}

	public XmlFormat(final String xml, final int indent) {
		this.xml = xml;
		this.indent = indent;
	}

	@Override
	public String toString() {
		return format(
			xml.replaceAll("(?m)^[ \t]*\r?\n", "").replaceAll("[\r]*\n", ""),			
			indent
		).replaceAll("<\\?xml version.*\\?>[\r]*\n", "");
	}

	private String format(final String input, final int indent) {
		try {
			final Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(
					"{http://xml.apache.org/xslt}indent-amount", indent + "");
			final StreamResult result = new StreamResult(new StringWriter());
			final DOMSource source = new DOMSource(parseXml(input));
			transformer.transform(source, result);
			return result.getWriter().toString();
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Document parseXml(final String in) {
		try {
			final DocumentBuilderFactory dbf = DocumentBuilderFactory
					.newInstance();
			final DocumentBuilder db = dbf.newDocumentBuilder();
			final InputSource is = new InputSource(new StringReader(in));
			return db.parse(is);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}
}
