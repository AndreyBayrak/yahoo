package yahoo.finance.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyDomParser
{
    private InputStream inputStream;

    public MyDomParser(String xmlRecord) throws IOException
    {
        this.inputStream = new ByteArrayInputStream(xmlRecord.getBytes());
    }


    private String getNodeValue(Element element, String nodeName)
    {
        NodeList nodes = element.getElementsByTagName(nodeName);

        if (nodes.getLength() > 0)
            return nodes.item(0).getTextContent();

        return null;
    }


    public void printParseResults() throws ParserConfigurationException, IOException, SAXException
    {
        System.out.println("DOM PARSER RESULTS:\n");

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.parse(inputStream);
        NodeList nodes = document.getElementsByTagName("rate");

        if (nodes.getLength() == 0) {
            System.out.println("Any 'rate' nodes are not found in the current XML record.");
            return;
        }

        Results results = new Results();

        for (int i = 0; i < nodes.getLength(); i++) {
            Element element = (Element) nodes.item(i);

            Rate rate = new Rate();
            rate.setName(getNodeValue(element, "Name"));
            rate.setDate(getNodeValue(element, "Date"));
            rate.setTime(getNodeValue(element, "Time"));
            rate.setRate(getNodeValue(element, "Rate"));
            rate.setAsk(getNodeValue(element, "Ask"));
            rate.setBid(getNodeValue(element, "Bid"));

            results.add(rate);
        }

        System.out.println(results);
    }
}
