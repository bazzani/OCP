package ocp.C5;

import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import static java.time.Month.MARCH;
import static java.time.Month.SEPTEMBER;

/**
 * Created by barry on 25/04/2017.
 */
public class MainC5 {
    public static void main(String[] args) throws ParseException {
        new Locale("hi");
        new Locale("hi", "IN");
        System.out.println();
//---------------------------

//        Locale.setDefault(new Locale("en", "US"));
//        ResourceBundle b = ResourceBundle.getBundle("Dolphins");
//        String name = b.getString("name");
//        System.out.println("name = " + name);
//        System.out.println();
//---------------------------

        LocalDate verosBirthday = LocalDate.of(2012, SEPTEMBER, 19);
        System.out.println("verosBirthday = " + verosBirthday);
        System.out.println();
//---------------------------

//        LocalDate date =
//                LocalDate.parse("2018-04-30", DateTimeFormatter.ISO_LOCAL_DATE);
//        date.plusDays(2);
//        date.plusHours
        System.out.println();
//---------------------------

        LocalDateTime d = LocalDateTime.of(2017, 4, 25, 21, 58, 59);
        Period p = Period.of(1, 2, 3);
        d = d.minus(p);
        DateTimeFormatter f = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        DateTimeFormatter f2 = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
//        DateTimeFormatter f3 = ofLocalizedTime(FormatStyle.LONG);
//        DateTimeFormatter f4 = ofLocalizedTime(FormatStyle.FULL);
        System.out.println(d.format(f));
        System.out.println(d.format(f2));
//        System.out.println(d.format(f3));
//        System.out.println(d.format(f4));

        System.out.println();
//---------------------------

        LocalDate date = LocalDate.of(2017, MARCH, 13);
        LocalTime time = LocalTime.of(1, 30);
        ZoneId zone = ZoneId.of("US/Eastern");

        ZonedDateTime dateTime1 = ZonedDateTime.of(date, time, zone);
        ZonedDateTime dateTime2 = dateTime1.plus(1, ChronoUnit.HOURS);

        long hours = ChronoUnit.HOURS.between(dateTime1, dateTime2);
        int clock1 = dateTime1.getHour();
        int clock2 = dateTime2.getHour();

        System.out.println(String.format("hours %d, clock1 %d, clock2 %d", hours, clock1, clock2));
        System.out.println();
////---------------------------

        ZoneId zoneZ = ZoneId.of("US/Eastern");
        LocalDate dateZ = LocalDate.of(2016, 3, 20);
        LocalTime timeZ = LocalTime.of(2, 15);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(dateZ, timeZ, zone);
        System.out.println("a = " + zonedDateTime);
        System.out.println();
////---------------------------

        String m1 = Duration.of(1, ChronoUnit.MINUTES).toString();
        String m2 = Duration.ofMinutes(1).toString();
        String s = Duration.of(60, ChronoUnit.SECONDS).toString();
        System.out.println("m1 = " + m1);
        System.out.println("m2 = " + m2);
        System.out.println("s = " + s);

        String dDay = Duration.ofDays(1).toString();
        String pDay = Period.ofDays(1).toString();
        System.out.println("dDay = " + dDay);
        System.out.println("pDay = " + pDay);

        System.out.println();
////---------------------------

        System.out.println(Instant.now());
        System.out.println(Instant.ofEpochSecond(0));

//        LocalDate.now().toInstant();
//        LocalTime.now().toInstant();
//        LocalDateTime.now().toInstant();
        System.out.println(LocalDateTime.now().toInstant(ZoneOffset.UTC));

        System.out.println();
////---------------------------

        Properties props = new Properties();
        props.put("Vero", 123);
        props.put("Mayu", "Evans");
        props.keySet().stream().map(k -> props.get(k)).forEach(System.out::println);
        System.out.println();
////---------------------------

        LocalDate l = LocalDate.of(2016, MARCH, 13);
        LocalTime t = LocalTime.of(2, 30);
        ZoneId z = ZoneId.of("US/Eastern");
        ZonedDateTime zdt = ZonedDateTime.of(l, t, z);
        System.out.println("zdt = " + zdt);
        System.out.println();
////---------------------------

        ZoneId.getAvailableZoneIds()
                .stream()
                .sorted()
//                .forEach(System.out::println)
        ;
        System.out.println();
////---------------------------

        StringBuilder b = new StringBuilder();
        b.append("12345").append("-");

        StringBuilder b2 = b.reverse();
        b2.codePoints().forEach(System.out::println);

        b2.delete(1, 4);
        System.out.println("b2 = " + b2);

        System.out.println(b == b2);
        System.out.println();
////---------------------------

        Locale locale = Locale.getDefault();
        System.out.println("locale = " + locale);
        System.out.println();

        Locale locale1 = new Locale.Builder()
                .setLanguage("hi")
                .setRegion("IN")
                .build();
        System.out.println("locale1 = " + locale1);

        System.out.println();
////---------------------------

////---------------------------
//  Resource Bundles
////---------------------------
        Locale rbLocale = new Locale("en", "CA");
        ResourceBundle rb = ResourceBundle.getBundle("Zoo", rbLocale);

        System.out.print(rb.getString("hello"));
        System.out.print(". ");
        System.out.print(rb.getString("name"));
        System.out.print(" ");
        System.out.print(rb.getString("open"));
        System.out.print(" ");
        System.out.println(rb.getString("visitor"));
        System.out.println();

        String format = rb.getString("helloByName");
        String formatted = MessageFormat.format(format, "Veronica");
        System.out.println("formatted = " + formatted);
        System.out.println();
//---------------------------

        NumberFormat nf = NumberFormat.getInstance();
        String one = "456abc";
        String two = "-2.5165x10";
        String three = "x85.3";
        System.out.println(nf.parse(one));
        System.out.println(nf.parse(two));
//        System.out.println(nf.parse(three));

        System.out.println();
//---------------------------

        String amt = "€92,807.99";
        NumberFormat cf = NumberFormat.getCurrencyInstance();

        String formattedValue = cf.format(92807.99);
        System.out.println("formattedValue = " + formattedValue);

        double parsedValue = (Double) cf.parse(amt);
        System.out.println("parsedValue = " + parsedValue);

        System.out.println();
//---------------------------

        LocalDate ldn = LocalDate.now();
        LocalTime ltn = LocalTime.now();
        LocalDateTime ldtn = LocalDateTime.now();

        DateTimeFormatter shortDatetime =
                DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);

        System.out.println(shortDatetime.format(ldn));
        System.out.println(shortDatetime.format(ldtn));
//        System.out.println(shortDatetime.format(ltn));

        System.out.println(ldn.format(shortDatetime));
        System.out.println(ldtn.format(shortDatetime));
//        System.out.println(ltn.format(shortDatetime));
        System.out.println();
//---------------------------

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM dd yyyy");
        LocalDate fld = LocalDate.parse("01 02 2015", dtf);
        LocalDate fld2 = LocalDate.parse("2015-01-02");
        LocalTime flt = LocalTime.parse("11:22");

        System.out.println("fld = " + fld);
        System.out.println("fld2 = " + fld2);
        System.out.println("flt = " + flt);
        System.out.println();
//---------------------------

        LocalDate tdate = LocalDate.of(2016, MARCH, 13);
        LocalTime ttime = LocalTime.of(1, 30);
        ZoneId tzoneId = ZoneId.of("US/Eastern");
        ZonedDateTime tdateTime1 = ZonedDateTime.of(tdate, ttime, tzoneId);
        ZonedDateTime tdateTime2 = tdateTime1.plus(1, ChronoUnit.HOURS);

        System.out.println("tdateTime1 = " + tdateTime1);
        System.out.println("tdateTime2 = " + tdateTime2);

        long thours = ChronoUnit.HOURS.between(tdateTime1, tdateTime2);
        int tclock1 = tdateTime1.getHour();
        int tclock2 = tdateTime2.getHour();

        System.out.println("thours = " + thours);
        System.out.println("tclock1 = " + tclock1);
        System.out.println("tclock2 = " + tclock2);

        System.out.println();
        //---------------------------

        ZoneId zoneId = ZoneId.of("US/Eastern");

        LocalDate localDate0 = LocalDate.of(2016, 3, 12);
        LocalDate localDate1 = LocalDate.of(2016, 3, 13);
//                LocalDate.of(2016,3,40);
        LocalDate localDate2 = LocalDate.of(2016, 11, 6);
        LocalDate localDate3 = LocalDate.of(2016, 11, 7);
//                LocalDate.of(2017,2,29);

        LocalTime localTime = LocalTime.of(2, 15);
        ZonedDateTime zdt0 = ZonedDateTime.of(localDate0, localTime, zoneId);
        System.out.println("zdt0 = " + zdt0);

        ZonedDateTime zdt1 = ZonedDateTime.of(localDate1, localTime, zoneId);
        System.out.println("zdt1 = " + zdt1);

        ZonedDateTime zdt2 = ZonedDateTime.of(localDate2, localTime, zoneId);
        System.out.println("zdt2 = " + zdt2);

        ZonedDateTime zdt3 = ZonedDateTime.of(localDate3, localTime, zoneId);
        System.out.println("zdt3 = " + zdt3);
        //---------------------------
    }
}
