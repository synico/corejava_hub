package nick;

import java.time.ZonedDateTime;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println( "Hello World!" + zonedDateTime.toEpochSecond());
    }
}
