import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Temp {

    public static void main(String[] args) {
        BigDecimal bg = new BigDecimal(3.3);
        double bgb = Optional.ofNullable(bg).orElse(BigDecimal.valueOf(0)).doubleValue();
        Map<String, Object> result = new HashMap();
        result.put("key", new Double(2.111));
        result.put("bg", bgb);
        System.out.println((Double)result.get("bg"));

        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDate, localTime, ZoneId.systemDefault());
        System.out.println(zonedDateTime);
    }

}
