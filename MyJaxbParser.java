package yahoo.finance.parser;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyJaxbParser
{
    private InputStream inputStream;

    public MyJaxbParser(String xmlRecord) throws IOException
    {
        this.inputStream = new ByteArrayInputStream(xmlRecord.getBytes());
    }


    public void printParseResults() throws JAXBException
    {
        System.out.println("JAXB PARSER RESULTS:\n");

        JAXBContext jaxbContext = JAXBContext.newInstance(Query.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Query query = (Query) unmarshaller.unmarshal(inputStream);

        System.out.println(query);
    }
}
