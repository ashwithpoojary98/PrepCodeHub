package main.java.io.github.ashwithpoojary98.array;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ComparatorSample {

    public static void main(String[] args) throws ParseException {
        Date today = new Date();

        String date = "26-03-2026";

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date yestrdayDate = dateFormat.parse(date);

        System.out.println(yestrdayDate);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss z");
        System.out.println(sdf.format(new Date()));
        Instant instant=Instant.now();
        ZonedDateTime zonedDateTime=ZonedDateTime.now();
        System.out.println(instant);
        String zone=zonedDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"));
        System.out.println(zonedDateTime);
        System.out.println(zone);
    

    }

}
