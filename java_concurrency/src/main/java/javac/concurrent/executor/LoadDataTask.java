package javac.concurrent.executor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class LoadDataTask implements Callable<String> {

    private long coldTime = 0L;

    public void setColdTime(long coldTime) {
        this.coldTime = coldTime;
    }

    public LoadDataTask(long coldTime) {
        this.coldTime = coldTime;
    }

    @Override
    public String call() {
        System.out.println("#### start to run call, coldTime: " + this.coldTime);
        Calendar start = new GregorianCalendar();
        Calendar end = new GregorianCalendar();
        start.setTimeInMillis(System.currentTimeMillis());
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder sb = new StringBuilder();
        sb.append(dateformat.format(start.getTime()));
        sb.append(" | ");
        try {
            TimeUnit.SECONDS.sleep(coldTime);
            end.setTimeInMillis(System.currentTimeMillis());
            sb.append(dateformat.format(end.getTime()));
            sb.append(" | ");
            if (Thread.currentThread().getName().contains("3")) {

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        sb.append(Thread.currentThread().getName());
        sb.append(" | ");
        sb.append(Long.toString(this.coldTime));
        System.out.println("#### " + sb.toString());
        return sb.toString();
    }

}
