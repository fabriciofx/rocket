package com.github.fabriciofx.rocket.stream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public final class StreamXml implements StreamData {
	private Document doc;

	public StreamXml() {
		try {
			doc = DocumentBuilderFactory
				.newInstance()
				.newDocumentBuilder()
				.newDocument();
		} catch (final ParserConfigurationException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public Element element(final String name) {
		final Element e = doc.createElement(name);
		doc.appendChild(e);
		return e;
	}

	public Element elementByXPath(final String xpath) {
		final XPath path = XPathFactory.newInstance().newXPath();
		try {
			final NodeList nodes = (NodeList) path
				.evaluate(
					xpath,
					doc.getDocumentElement(),
					XPathConstants.NODESET
				);
			return (Element) nodes.item(0);
		} catch (final XPathExpressionException e) {
			throw new RuntimeException(e);
		}
	}

	public Element element(final Element parent, final String name) {
		final Element e = doc.createElement(name);
		parent.appendChild(e);
		return e;
	}

	public Element element(final Element parent, final String name,
			final String value) {
		final Element e = doc.createElement(name);
		e.appendChild(doc.createTextNode(value));
		parent.appendChild(e);
		return e;
	}
	
	public Element element(final Element parent, final StreamData stream) {
		final String xml = stream.toString();
		try {
			final Document doc2 = DocumentBuilderFactory
				.newInstance()
				.newDocumentBuilder()
				.parse(
					new ByteArrayInputStream(
						xml.getBytes()
					)
				);
			final Node node = doc.importNode(doc2.getDocumentElement(), true);
			cleanEmptyTextNodes(node);			
			parent.appendChild(node);
		} catch (final SAXException | IOException
				| ParserConfigurationException e) {
			throw new RuntimeException(e);
		}
		return doc.getDocumentElement();
	}

	public void delete(final String xpath) throws IOException {
        final XPathFactory xpf = XPathFactory.newInstance();
        final XPath xp = xpf.newXPath();
		try {
	        final XPathExpression expression = xp.compile(xpath);
	        final Node node = (Node) expression
	        	.evaluate(doc, XPathConstants.NODE);
	        node.getParentNode().removeChild(node);
	        cleanEmptyTextNodes(doc.getDocumentElement());
		} catch (final XPathExpressionException e) {
			throw new IOException(e);
		}
	}
	
	@Override
	public void write(final OutputStream stream) throws IOException {
		final TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		try {
			final Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(
					"{http://xml.apache.org/xslt}indent-amount", "2");
			final DOMSource source = new DOMSource(doc);
			final StreamResult result = new StreamResult(stream);
			transformer.transform(source, result);
		} catch (final TransformerException e) {
			throw new IOException(e);
		}
	}

	private static void cleanEmptyTextNodes(Node parentNode) {
        boolean removeEmptyTextNodes = false;
        Node childNode = parentNode.getFirstChild();
        while (childNode != null) {
            removeEmptyTextNodes |= checkNodeTypes(childNode);
            childNode = childNode.getNextSibling();
        }

        if (removeEmptyTextNodes) {
            removeEmptyTextNodes(parentNode);
        }
    }

    private static void removeEmptyTextNodes(Node parentNode) {
        Node childNode = parentNode.getFirstChild();
        while (childNode != null) {
            // grab the "nextSibling" before the child node is removed
            Node nextChild = childNode.getNextSibling();

            short nodeType = childNode.getNodeType();
            if (nodeType == Node.TEXT_NODE) {
                boolean containsOnlyWhitespace = childNode.getNodeValue()
                        .trim().isEmpty();
                if (containsOnlyWhitespace) {
                    parentNode.removeChild(childNode);
                }
            }
            childNode = nextChild;
        }
    }

    private static boolean checkNodeTypes(Node childNode) {
        short nodeType = childNode.getNodeType();

        if (nodeType == Node.ELEMENT_NODE) {
            cleanEmptyTextNodes(childNode); // recurse into subtree
        }

        if (nodeType == Node.ELEMENT_NODE
                || nodeType == Node.CDATA_SECTION_NODE
                || nodeType == Node.COMMENT_NODE) {
            return true;
        } else {
            return false;
        }
    }
    
	@Override
	public void read(final InputStream stream) throws IOException {
		try {
			final DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			final DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(stream);
		} catch (final ParserConfigurationException | SAXException e) {
			throw new IOException(e);
		}
	}
	
	@Override
	public String toString() {
		final ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			write(os);
			return new String(os.toByteArray(),"UTF-8");
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int size() throws IOException {
		return 0;
	}
}
