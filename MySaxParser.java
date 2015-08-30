package yahoo.finance.parser;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.StringReader;


public class MySaxParser extends DefaultHandler
{
    private InputSource inputSource;
    private Results results;
    private Rate rate;
    private String nodeName;
    private String nodeValue;


    public MySaxParser() {}

    public MySaxParser(String xmlRecord) throws IOException
    {
        this.inputSource = new InputSource(new StringReader( xmlRecord));
    }

    @Override
    public void startDocument() throws SAXException
    {
        results = new Results();
    }

    @Override
    public void endDocument() throws SAXException
    {
        System.out.println("SAX PARSER RESULTS:\n");
        System.out.println(results);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts)
    {
        if ("rate".equals(localName))
            rate = new Rate();

        nodeName = localName;
    }

    @Override
    public void endElement(String uri, String localName, String qName)
    {
        if ("rate".equals(localName))
            results.add(rate);
    }

    @Override
    public void characters(char[] ch, int start, int length)
    {
        nodeValue = new String(ch, start, length);

        if ("Name".equals(nodeName))
            rate.setName(nodeValue);
        else if ("Date".equals(nodeName))
            rate.setDate(nodeValue);
        else if ("Time".equals(nodeName))
            rate.setTime(nodeValue);
        else if ("Rate".equals(nodeName))
            rate.setRate(nodeValue);
        else if ("Ask".equals(nodeName))
            rate.setAsk(nodeValue);
        else if ("Bid".equals(nodeName))
            rate.setBid(nodeValue);
    }


    public void printParseResults() throws ParserConfigurationException, SAXException, IOException
    {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        saxParserFactory.setNamespaceAware(true);
        SAXParser saxParser = saxParserFactory.newSAXParser();

        XMLReader xmlReader = saxParser.getXMLReader();
        xmlReader.setContentHandler((ContentHandler) new MySaxParser());
        xmlReader.parse(inputSource);
    }
}
