package yahoo.finance.parser;

import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main
{
    public static void main(String[] args) throws   IOException, ParserConfigurationException,
                                                    SAXException, JAXBException
    {
        final String request = "http://query.yahooapis.com/v1/public/yql?format=xml&q=select%20*%20from%20" +
                "yahoo.finance.xchange%20where%20pair%20in%20(\"USDEUR\",%20\"USDUAH\")&env=store://datatables.org/alltableswithkeys";

        final String xmlRecord = getXmlRecord(request);

        System.out.println("\nXML RECORD:\n");
        System.out.println(xmlRecord + "\n");

        // выводим результаты парсера DOM
        MyDomParser myDomParser = new MyDomParser(xmlRecord);
        myDomParser.printParseResults();

        // выводим результаты парсера JAXB
        MyJaxbParser myJaxbParser = new MyJaxbParser(xmlRecord);
        myJaxbParser.printParseResults();

        // выводим результаты парсера SAX
        MySaxParser mySaxParser = new MySaxParser(xmlRecord);
        mySaxParser.printParseResults();
    }


    public static String getXmlRecord(String request) throws IOException
    {
        URL url = new URL(request);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        StringBuilder stringBuilder = new StringBuilder();

        try {
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(http.getInputStream()));
            char[] buffer = new char[1000000];
            int result;

            do {
                if ((result = bufferedReader.read(buffer)) > 0)
                    stringBuilder.append(new String(buffer, 0, result));
            } while (result > 0);
        } finally {
            http.disconnect();
        }

        return stringBuilder.toString();
    }
}
