package yahoo.finance.parser;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.ParseException;


@XmlRootElement(name = "rate")
public class Rate {
    private String name;
    private String rate;
    private String date;
    private String time;
    private String ask;
    private String bid;

    public Rate(){}

    public Rate(String name, String rate, String date,
                String time, String ask, String bid) throws ParseException
    {
        this.name = name;
        this.rate = rate;
        this.date = date;
        this.time = time;
        this.ask = ask;
        this.bid = bid;
    }

    @XmlElement(name = "Name")
    public void   setName(String name) { this.name = name; }
    public String getName() { return name; }

    @XmlElement(name = "Rate")
    public void   setRate(String rate) { this.rate = rate; }
    public String getRate() { return rate; }

    @XmlElement(name = "Date")
    public void   setDate(String date) { this.date = date; }
    public String getDate() { return date; }

    @XmlElement(name = "Time")
    public void   setTime(String time) { this.time = time; }
    public String getTime() { return time; }

    @XmlElement(name = "Ask")
    public void   setAsk(String ask) { this.ask = ask; }
    public String getAsk() { return ask; }

    @XmlElement(name = "Bid")
    public void   setBid(String bid) { this.bid = bid; }
    public String getBid() { return bid; }

    @Override
    public String toString()
    {
        return "Name:  " + name + "\n" +
               "Date:  " + date + "\n" +
               "Time:  " + time + "\n" +
               "Rate = " + rate + "\n" +
               "Ask  = " + ask  + "\n" +
               "Bid  = " + bid  + "\n\n";
    }
}
