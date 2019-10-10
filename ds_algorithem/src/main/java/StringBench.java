import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;

public class StringBench {

    public static void main(String[] args) {
        String str = "\"015AZ3.9-Z(TPL3,F)\"";
        String[] stra = str.split("\",");
        for (String s : stra) {
            System.out.println(s);
        }
        System.out.println(str.replaceAll("\"", ""));

        Calendar cal = Calendar.getInstance();
        TimeZone timeZone = cal.getTimeZone();
        System.out.println(timeZone.getID());
        System.out.println(timeZone.getDisplayName());

        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            LocalTime strLocalTime = LocalTime.parse("x", dateTimeFormatter);
            System.out.println(strLocalTime);
        } catch (Exception ex) {
            System.out.println("x");
        }

//        System.out.println(localTime.isAfter(strLocalTime));

    }

}
