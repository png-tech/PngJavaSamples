package png.samples.xml.domread;


import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XmlDomReadSample {

    public static void main(String [] args) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        f.setValidating(false);
        f.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = f.newDocumentBuilder();
        Document doc = builder.parse(XmlDomReadSample.class.getResourceAsStream("dom-read-sample-xml.xml"));
        
        printDom("",doc.getFirstChild());
        
    }

    private static void printDom(String prefix, Node node) {
        
        String text = node.getNodeValue();
        if (text != null && !text.isEmpty()) {
            System.out.println(prefix + " value = \"" + text + "\"");
        }
        NamedNodeMap attributes = node.getAttributes();
        if (attributes != null) {
            for (int i = 0 ; i < attributes.getLength(); i++) {
                Node attr = attributes.item(i);
                System.out.println(prefix +
                        " attr = \"" + attr.getNodeName()  + "\"" +
                        " value = \"" + attr.getTextContent().toString() + "\" ");
            }
        }

        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            printDom(prefix + node.getNodeName() + ":", children.item(i));
        }
    }
}
