package yahoo.finance.parser;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "results")
public class Results
{
    @XmlElement(name = "rate")
    private List<Rate> rates = new ArrayList<Rate>();

    public void add(Rate rate) { rates.add(rate); }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        for (Rate rate : rates)
            stringBuilder.append(rate.toString());

        return stringBuilder.toString();
    }
}
