package png.samples.xml.saxread;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;

// SAX
import org.xml.sax.Attributes;
// import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 *
 * @author nicolay
 */
public class XmlSaxReadSample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        factory.setValidating(true);
        factory.setNamespaceAware(false);
        SAXParser parser;

        InputStream xmlData = null;
        try {
            // xmlData = new FileInputStream("sax-read-sample-xml.xml");
            xmlData = XmlSaxReadSample.class.getResourceAsStream("sax-read-sample-xml.xml");

            parser = factory.newSAXParser();
            parser.parse(xmlData, new MyParser());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // обработки ошибки, файл не найден
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            // обработка ошибки Parser
        } catch (SAXException e)
        {
            e.printStackTrace();
            // обработка ошибки SAX
        } catch (IOException e)
        {
            e.printStackTrace();
            // обработка ошибок ввода
        }

    }
}
// http://java.sun.com/j2se/1.4.2/docs/api/org/xml/sax/helpers/DefaultHandler.html
class MyParser extends DefaultHandler {

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        System.out.println("Тег: "+qName);
        if (qName.equals("book")) {
            System.out.println("id книги "+attributes.getValue("id"));
        }
        //      System.out.println(attributes.getLength());
        super.startElement(uri, localName, qName, attributes);


    }

    @Override
    public void characters(char[] c, int start, int length)
            throws SAXException {
        super.characters(c, start,  length);
        for (int i=start;i< start+length;++i) {
            System.err.print(c[i]);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        System.out.println("Тег разобран: "+qName);
        super.endElement(uri,localName, qName);
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Начало разбора документа!");
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("Разбор документа окончен!");

    }

}

